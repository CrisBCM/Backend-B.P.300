package com.api.security.dto;

import com.api.security.publicacion.Publicacion;
import com.api.security.shared.Tema;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class PublicacionResumenDTO {
    
    private int id;
    
    private String titulo;
    
    private String contenido;
    private String categoria;
    private LocalDateTime fecha;
    
    private String autor;
    
    private String fotoAutor;
    
    public PublicacionResumenDTO(Publicacion publicacion){
        id = publicacion.getId();
        titulo = publicacion.getTitulo();
        contenido = publicacion.getContenido();
        fecha = publicacion.getFecha();
        autor = publicacion.getAutor();
        fotoAutor = publicacion.getFotoAutor();
        categoria = publicacion.getCategoria().getNombre();
    }
}
