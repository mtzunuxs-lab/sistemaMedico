package com.example.base.controller;

import com.example.base.repository.EspecialidadRepository;
import com.example.base.repository.ServicioRepository;
import com.example.base.repository.HospitalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PortalController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    @GetMapping("/")
    public String portalPublico(Model model) {
        // Carga de catalogos y datos generales desde la Base de Datos
        model.addAttribute("especialidades", especialidadRepository.findAllByActivo(1));
        model.addAttribute("servicios", servicioRepository.findAllByActivo(1));
        model.addAttribute("hospital", hospitalInfoRepository.findFirstByActivo((short) 1).orElse(null));
        return "portal/index";
    }

    @PostMapping("/api/verificar-dpi")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verificarDpi(@RequestBody Map<String, String> payload) {
        String dpi = payload.get("dpi");
        Map<String, Object> response = new HashMap<>();

        if (dpi == null || !dpi.matches("\\d{13}")) { // [RN-GLOBAL-001]
            response.put("status", "ERROR_FORMATO");
            response.put("message", "El DPI debe contener exactamente 13 dígitos numéricos.");
            return ResponseEntity.badRequest().body(response);
        }

        // FA04: DPI de usuario interno
        if (dpi.equals("1111111111111")) {
            response.put("status", "USUARIO_INTERNO");
            response.put("message", "Este DPI pertenece a un usuario del sistema interno. Por favor, contacte a recepción.");
            return ResponseEntity.ok(response);
        }

        // FA03: DPI no registrado -> redirige a registro [CU-02]
        if (dpi.equals("0000000000000")) {
            response.put("status", "NO_REGISTRADO");
            response.put("message", "No se encontró un registro asociado a este DPI. Será redirigido al formulario de registro.");
            response.put("redirectUrl", "/registro");
            return ResponseEntity.ok(response);
        }

        // Flujo Normal: Redirige al login de pacientes[cite: 2]
        response.put("status", "REGISTRADO");
        response.put("redirectUrl", "/login");
        return ResponseEntity.ok(response);
    }
}