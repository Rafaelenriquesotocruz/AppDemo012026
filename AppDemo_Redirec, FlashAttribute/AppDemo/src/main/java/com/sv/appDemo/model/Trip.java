package com.sv.appDemo.model;

import java.util.Date;

public class Trip {
    private Long id;
    private String nombre;
    private String descripcion;
    private String detalles;
    private Date fecha;
    private Double costo;
    private Integer destacado;
    private String imagen;
    private Categoria categoria;

    public Trip() {}


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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getDestacado() {
        return destacado;
    }

    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", detalles='" + detalles + '\'' +
                ", fecha=" + fecha +
                ", costo=" + costo +
                ", destacado=" + destacado +
                ", imagen='" + imagen + '\'' +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "null") +
                '}';
    }
}