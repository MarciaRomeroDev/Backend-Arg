
package com.backportfolio.API.DTO;

import jakarta.validation.constraints.NotBlank;


public class HabilidadesDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String icono;
    private int progreso;

    public HabilidadesDTO() {
    }

    public HabilidadesDTO(String nombre, String icono, int progreso) {
        this.nombre = nombre;
        this.icono = icono;
        this.progreso = progreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }
    
}
