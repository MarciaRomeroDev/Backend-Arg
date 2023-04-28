
package com.backportfolio.API.DTO;

import jakarta.validation.constraints.NotBlank;


public class PersonaDTO {
     @NotBlank
    private String nombre;
    @NotBlank
    private  String apellido;
 
    private String foto_perfil;
    @NotBlank
    private String descripcion;

    public PersonaDTO() {
    }

    public PersonaDTO(String nombre, String apellido, String foto_perfil, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto_perfil = foto_perfil;
        this.descripcion = descripcion;
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

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
