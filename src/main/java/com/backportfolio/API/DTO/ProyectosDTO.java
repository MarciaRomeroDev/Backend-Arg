
package com.backportfolio.API.DTO;

import jakarta.validation.constraints.NotBlank;

public class ProyectosDTO {
     @NotBlank  (message = "el titulo del proyecto es obligatorio")
    private String titulo;
    @NotBlank  (message = "Se debe poner una breve descrpcion del proyecto")
    private String descripcion;
    @NotBlank  (message = "la url de la imagen del proyecto es obligatora")
    private String url_imagen;
    @NotBlank  (message = "la url del repositorio del proyecto es obligatorio")
    private String url_repositorio;

    public ProyectosDTO() {
    }

    public ProyectosDTO(String titulo, String descripcion, String url_imagen, String url_repositorio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url_imagen = url_imagen;
        this.url_repositorio = url_repositorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getUrl_repositorio() {
        return url_repositorio;
    }

    public void setUrl_repositorio(String url_repositorio) {
        this.url_repositorio = url_repositorio;
    }
    
    
}
