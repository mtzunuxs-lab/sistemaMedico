package com.example.base.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // El ID solo lleva @Id y @GeneratedValue

    private String nombre;
    private String apellido;
    private String carnet;
    private String email;

    // La relación con la carrera va sobre el objeto Carrera
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    // Columna para eliminación lógica (1 = activo, 0 = eliminado)
    @Column(nullable = false)
    private Integer activo = 1;

    public Estudiante() {}

    public Estudiante(String nombre, String apellido, String carnet, String email, Carrera carrera, Integer activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
        this.email = email;
        this.carrera = carrera;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}