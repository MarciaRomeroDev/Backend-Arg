
package com.backportfolio.API.DTO;

import jakarta.validation.constraints.NotBlank;

public class EstudiosDTO {
    @NotBlank(message = "El titulo del estudio es obligatorio")
    private String titulo;
    @NotBlank (message = "La fecha inicio-fin es obligatoria")
    private String fecha;
    @NotBlank (message = "El nombre de la institución es obligatoria")
    private String lugar;
    @NotBlank (message = "Se debe poner una pequeña descripcion")
    private String descripcion;

    public EstudiosDTO() {
    }

    public EstudiosDTO(String titulo, String fecha, String lugar, String descripcion) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
