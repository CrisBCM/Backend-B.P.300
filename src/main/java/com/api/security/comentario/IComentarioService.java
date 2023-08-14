package com.api.security.comentario;

import com.api.security.persona.Persona;
import java.util.List;


public interface IComentarioService {
    public Comentario añadirComentario(int idPublicacion, int idPersona, String contenido);
    public List<Comentario> cambiarFotoComentario(Persona persona);
    public void meGusta(int idComentario, String nombreUsuario);
    public void noMeGusta(int idComentario, String nombreUsuario);
    public void eliminarComentario(int idComentario);
    public Comentario editarComentario(int idComentario, String nuevoContenido);
}
