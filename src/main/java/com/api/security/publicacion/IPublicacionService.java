package com.api.security.publicacion;

import com.api.security.persona.Persona;
import java.util.List;


public interface IPublicacionService {
    public List<Publicacion> obtenerPublicaciones();
    public Publicacion añadirPublicacion(int idPersona, PublicacionRequest publicacion);
    public void eliminarPublicacion(int idPublicacion);
    public Publicacion editarPublicacion(int idPublicacion, PublicacionRequest publicacionRequest);
    public List<Publicacion> actualizarFotoPublicacion(Persona persona);
}
