package com.api.security.respuesta;

import java.util.List;


public interface IRespuestaService {
    public Respuesta añadirRespuesta(int idComentario, int idPersona, String contenido);
    public void eliminarRespuesta(int idRespuesta);
    public Respuesta editarRespuesta(int idRespuesta, String contenido);
    public void meGusta(String nombreUsuario, int idRespuesta);
    public void noMeGusta(String nombreUsuario, int idRespuesta);
}
