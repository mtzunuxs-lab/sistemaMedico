package com.example.base.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sys_informacion_hospital")
public class HospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(length = 15)
    private String nit;

    @Column(name = "ruta_logo")
    private String rutaLogo;

    @Column(name = "ruta_banner")
    private String rutaBanner;

    @Column(nullable = false)
    private Short activo = 1;

    // Constructor vacío
    public HospitalInfo() {
    }

    // Constructor completo
    public HospitalInfo(Long id, String nombre, String direccion, String telefono, String correo, String nit, String rutaLogo, String rutaBanner, Short activo) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.nit = nit;
        this.rutaLogo = rutaLogo;
        this.rutaBanner = rutaBanner;
        this.activo = activo;
    }

    // Getters y Setters
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }

    public void setRutaLogo(String rutaLogo) {
        this.rutaLogo = rutaLogo;
    }

    public String getRutaBanner() {
        return rutaBanner;
    }

    public void setRutaBanner(String rutaBanner) {
        this.rutaBanner = rutaBanner;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }
}