package com.api.security.publicacion;

import java.util.List;


public interface IPublicacionService {
    public List<Publicacion> obtenerPublicaciones();
    public Publicacion añadirPublicacion(int idPersona, Publicacion publicacion);
    public void eliminarPublicacion(int idPublicacion);
    public Publicacion editarPublicacion(int idPublicacion, PublicacionRequest publicacionRequest);
}
