package com.sv.jpa.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private Double costo;
    private Integer destacado;
    private String imagen;
    private String estatus;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    @Transient 
    private String pruebaTemporal;

    public Trip() {
    }

    public Trip(String nombre, String descripcion, LocalDate fecha, Double costo, 
                Integer destacado, String imagen, String estatus, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
        this.destacado = destacado;
        this.imagen = imagen;
        this.estatus = estatus;
        this.categoria = categoria;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public Integer getDestacado() { return destacado; }
    public void setDestacado(Integer destacado) { this.destacado = destacado; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getPruebaTemporal() { return pruebaTemporal; }
    public void setPruebaTemporal(String pruebaTemporal) { this.pruebaTemporal = pruebaTemporal; }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", costo=" + costo +
                ", destacado=" + destacado +
                ", imagen='" + imagen + '\'' +
                ", estatus='" + estatus + '\'' +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "null") +
                '}';
    }
}