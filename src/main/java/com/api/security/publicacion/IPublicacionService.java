package com.api.security.publicacion;

import com.api.security.dto.PublicacionRequestDTO;
import com.api.security.persona.Persona;
import java.util.List;


public interface IPublicacionService {
    public List<Publicacion> obtenerPublicaciones();
    public Publicacion añadirPublicacion(int idPersona, PublicacionRequest publicacion);
    public void eliminarPublicacion(int idPublicacion);
    public Publicacion editarPublicacion(int idPublicacion, PublicacionRequestDTO publicacionRequest);
    public List<Publicacion> actualizarFotoPublicacion(Persona persona);
    public void meGusta(int publicacionId, String nombreUsuario);
    public void noMeGusta(int publicacionId, String nombreUsuario);
}
