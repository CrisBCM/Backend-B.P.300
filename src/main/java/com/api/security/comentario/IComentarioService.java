package com.api.security.comentario;


public interface IComentarioService {
    public Comentario añadirComentario(int idPublicacion, int idPersona, ComentarioDto comentario);
}
