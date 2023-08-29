package com.api.security.dto;

import com.api.security.comentario.Comentario;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ComentarioDTO {
    private int id;
    private String autor;
    private String fotoAutor;
    private String contenido;
    private LocalDateTime fecha;
    private String nombrePublicacion;
    private int publicacionId;
    private Set<String> listaMeGusta = new HashSet<>();
    private Set<String> listaNoMeGusta = new HashSet<>();
    private List<RespuestaDTO> respuestas = new ArrayList<>();
    
    public ComentarioDTO(Comentario comentario){
        id = comentario.getId();
        autor = comentario.getAutor();
        fotoAutor = comentario.getFotoAutor();
        contenido = comentario.getContenido();
        fecha = comentario.getFecha();
        nombrePublicacion = comentario.getPublicacion().getTitulo();
        publicacionId = comentario.getPublicacion().getId();
        listaMeGusta = comentario.getListaMeGusta();
        listaNoMeGusta = comentario.getListaNoMeGusta();
        respuestas = comentario.getRespuestas()
                                .stream()
                                .map(respuesta -> new RespuestaDTO(respuesta))
                                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getFotoAutor() {
        return fotoAutor;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }
    

    public Set<String> getListaMeGusta() {
        return listaMeGusta;
    }

    public Set<String> getListaNoMeGusta() {
        return listaNoMeGusta;
    }

    public List<RespuestaDTO> getRespuestas() {
        return respuestas;
    }

    public int getPublicacionId() {
        return publicacionId;
    }
    
    
}
