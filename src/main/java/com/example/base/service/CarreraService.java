package com.example.base.service;

import com.example.base.model.Carrera;
import com.example.base.repository.CarreraRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Cacheable("catalogoCarreras")
    public List<Carrera> obtenerCarrerasActivas() {
        System.out.println("--> CONSULTA DE CARRERAS A POSTGRESQL REALIZADA (Cargado en Caché)");
        return carreraRepository.findByActivo(1);
    }
}