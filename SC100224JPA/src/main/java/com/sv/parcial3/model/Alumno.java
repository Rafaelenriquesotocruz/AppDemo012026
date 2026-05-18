package com.sv.parcial3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String carnet;
    private String nombre;
    private String apellido;
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    public Alumno() {}

    public Alumno(String carnet, String nombre, String apellido, String estado, Carrera carrera) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.carrera = carrera;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", carnet='" + carnet + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estado='" + estado + '\'' +
                ", carrera=" + (carrera != null ? carrera.getNombre() : "null") +
                '}';
    }
}