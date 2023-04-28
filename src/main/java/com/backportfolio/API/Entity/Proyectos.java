
package com.backportfolio.API.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proyectos {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descripcion;
    private String url_imagen;

    public Proyectos() {
    }

    public Proyectos(String titulo, String descripcion, String url_imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url_imagen = url_imagen;
    }

    public Proyectos(String titulo, String descripcion, String url_imagen, String url_repositorio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setUrl_repositorio(String url_repositorio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
