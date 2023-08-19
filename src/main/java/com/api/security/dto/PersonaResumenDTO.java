package com.api.security.dto;

import com.api.security.persona.Persona;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class PersonaResumenDTO {
    private String nombreUsuario;
    private String nombreCompleto;
    private String imgUsuario;
    private Set<PublicacionResumenDTO> publicaciones = new HashSet<>();
    private Set<ComentarioResumenDTO> comentarios = new HashSet<>();
    
    public PersonaResumenDTO(Persona persona){
        nombreUsuario = persona.getNombreUsuario();
        nombreCompleto = persona.getNombreCompleto();
        imgUsuario = persona.getImgAvatar().getPath();
        comentarios = persona.getComentarios()
                              .stream()
                              .map(comentario -> new ComentarioResumenDTO(comentario))
                              .collect(Collectors.toSet());
        publicaciones = persona.getPublicaciones()
                                .stream()
                                .map(publicacion -> new PublicacionResumenDTO(publicacion))
                                .collect(Collectors.toSet());
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getImgUsuario() {
        return imgUsuario;
    }

    public Set<PublicacionResumenDTO> getPublicaciones() {
        return publicaciones;
    }

    public Set<ComentarioResumenDTO> getComentarios() {
        return comentarios;
    }
    
    
}
