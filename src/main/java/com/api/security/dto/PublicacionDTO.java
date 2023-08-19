package com.api.security.dto;

import com.api.security.publicacion.Publicacion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class PublicacionDTO {
    private int id;
    
    private String titulo;
    
    private String contenido;
    
    private String tema;
    
    private LocalDateTime fecha;
    
    private String autor;
    
    private String fotoAutor;
    
    private List<ComentarioDTO> comentarios = new ArrayList<>();
    
    public PublicacionDTO(Publicacion publicacion){
        id = publicacion.getId();
        titulo = publicacion.getTitulo();
        contenido = publicacion.getContenido();
        tema = publicacion.getTema();
        fecha = publicacion.getFecha();
        autor = publicacion.getAutor();
        fotoAutor = publicacion.getFotoAutor();
        comentarios = publicacion.getComentarios()
                .stream()
                .map(comentario -> new ComentarioDTO(comentario))
                .collect(Collectors.toList());
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

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }
    
}
