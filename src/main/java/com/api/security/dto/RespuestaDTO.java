
package com.api.security.dto;

import com.api.security.respuesta.Respuesta;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


class RespuestaDTO {
    private int id;
    private String autor;
    private String fotoAutor;
    private String contenido;
    private LocalDateTime fecha;
    private Set<String> listaMeGusta = new HashSet<>();
    private Set<String> listaNoMeGusta = new HashSet<>();

    public RespuestaDTO(Respuesta respuesta) {
        id = respuesta.getId();
        autor = respuesta.getAutor();
        fotoAutor = respuesta.getFotoAutor();
        contenido = respuesta.getContenido();
        fecha = respuesta.getFecha();
        listaMeGusta = respuesta.getListaMeGusta();
        listaNoMeGusta = respuesta.getListaNoMeGusta();
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

    public Set<String> getListaMeGusta() {
        return listaMeGusta;
    }

    public Set<String> getListaNoMeGusta() {
        return listaNoMeGusta;
    }
}
