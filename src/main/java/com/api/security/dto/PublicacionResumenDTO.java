package com.api.security.dto;

import com.api.security.publicacion.Publicacion;
import java.time.LocalDateTime;


public class PublicacionResumenDTO {
    
    private int id;
    
    private String titulo;
    
    private String contenido;
    
    private String tema;
    
    private LocalDateTime fecha;
    
    private String autor;
    
    private String fotoAutor;
    
    public PublicacionResumenDTO(Publicacion publicacion){
        id = publicacion.getId();
        titulo = publicacion.getTitulo();
        contenido = publicacion.getContenido();
        tema = publicacion.getTema();
        fecha = publicacion.getFecha();
        autor = publicacion.getAutor();
        fotoAutor = publicacion.getFotoAutor();
    }

    public int getId(){
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getTema() {
        return tema;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getAutor() {
        return autor;
    }

    public String getFotoAutor() {
        return fotoAutor;
    }
    
}
