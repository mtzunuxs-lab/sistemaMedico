package com.example.base.repository;

import com.example.base.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Devuelve solo los estudiantes cuyo campo activo sea igual al parámetro enviado (1)
    List<Estudiante> findByActivo(Integer activo);
}