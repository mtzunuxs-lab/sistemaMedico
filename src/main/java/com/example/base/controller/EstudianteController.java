package com.example.base.controller;

import com.example.base.model.Estudiante;
import com.example.base.repository.EstudianteRepository;
import com.example.base.service.CarreraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;
    private final CarreraService carreraService;

    public EstudianteController(EstudianteRepository estudianteRepository, CarreraService carreraService) {
        this.estudianteRepository = estudianteRepository;
        this.carreraService = carreraService;
    }

    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteRepository.findByActivo(1));
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("carreras", carreraService.obtenerCarrerasActivas()); // Desde Caché
        return "estudiantes/lista";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        if (estudiante.getActivo() == null) {
            estudiante.setActivo(1);
        }
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow();
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("estudiantes", estudianteRepository.findByActivo(1));
        model.addAttribute("carreras", carreraService.obtenerCarrerasActivas()); // Desde Caché
        return "estudiantes/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow();
        estudiante.setActivo(0);
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }
}