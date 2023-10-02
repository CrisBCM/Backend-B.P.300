package com.api.security.dto;

import com.api.security.categoria.Categoria;
import com.api.security.publicacion.Publicacion;
import com.api.security.shared.Tema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
public class PublicacionDTO {
    private int id;
    
    private String titulo;
    
    private String contenido;
    private LocalDateTime fecha;
    
    private String autor;
    
    private String fotoAutor;
    
    private List<ComentarioDTO> comentarios = new ArrayList<>();
    private Set<String> listaMeGusta = new HashSet<>();
    private Set<String> listaNoMeGusta = new HashSet<>();
    private String categoria;
    private int puntuacion;
    
    public PublicacionDTO(Publicacion publicacion){
        id = publicacion.getId();
        titulo = publicacion.getTitulo();
        contenido = publicacion.getContenido();
        fecha = publicacion.getFecha();
        autor = publicacion.getAutor();
        fotoAutor = publicacion.getFotoAutor();
        comentarios = publicacion.getComentarios()
                .stream()
                .map(comentario -> new ComentarioDTO(comentario))
                .collect(Collectors.toList());
        listaMeGusta = publicacion.getListaMeGusta();
        listaNoMeGusta = publicacion.getListaNoMeGusta();
        categoria = publicacion.getCategoria().getNombre();
        puntuacion = publicacion.getPuntuacion();
    }
}
