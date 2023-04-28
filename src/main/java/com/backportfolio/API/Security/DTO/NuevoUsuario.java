
package com.backportfolio.API.Security.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


public class NuevoUsuario {
    @NotBlank (message = "Nombre  obligatorio")
    private String nombre;
    @NotBlank (message = "Nombre de usuario obligatorio")
    private String nombreUsuario;
    @Email (message = "direccion de email no válida")
    @NotBlank (message = "email obligatorio")
    private String email;
    @NotBlank (message = "contraseña obligatoria")
    private String password;
    private Set<String> roles = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
}
