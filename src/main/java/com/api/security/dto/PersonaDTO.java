package com.api.security.dto;

import com.api.security.persona.Persona;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PersonaDTO {
    private int id;
    private String nombreCompleto;
    private String nombreUsuario;
    private int pesoCorporal;
    private double cantidadActividad;
    private ImagenDTO imgAvatar;
    private EstomagoDTO estomago;
    private List<PublicacionDTO> publicaciones = new ArrayList<>();
    private List<ComentarioDTO> comentarios = new ArrayList<>();
    
    public PersonaDTO(Persona persona){
        id = persona.getId();
        nombreCompleto = persona.getNombreCompleto();
        nombreUsuario = persona.getNombreUsuario();
        pesoCorporal = persona.getPesoCorporal();
        cantidadActividad = persona.getCantidadActividad();
        imgAvatar = new ImagenDTO(persona.getImgAvatar());
        estomago = new EstomagoDTO(persona.getEstomago());
        publicaciones = persona.getPublicaciones()
                .stream()
                .map(publicacion -> new PublicacionDTO(publicacion))
                .collect(Collectors.toList());
        comentarios = persona.getComentarios()
                .stream()
                .map(comentario -> new ComentarioDTO(comentario))
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getPesoCorporal() {
        return pesoCorporal;
    }

    public double getCantidadActividad() {
        return cantidadActividad;
    }

    public ImagenDTO getImgAvatar() {
        return imgAvatar;
    }

    public EstomagoDTO getEstomago() {
        return estomago;
    }

    public List<PublicacionDTO> getPublicaciones() {
        return publicaciones;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }
    
}
