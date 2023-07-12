package com.api.security.publicacion;

import com.api.security.comida.Comida;
import com.api.security.persona.Persona;
import com.api.security.persona.PersonaRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService implements IPublicacionService{
    
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired PersonaRepository personaRepo;
    
    @Override
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public Publicacion añadirPublicacion(int idPersona, Publicacion publicacion) {
        
        Persona persona = personaRepo.findById(idPersona).orElse(null);
        
        publicacion.setPersona(persona);
        publicacion.setAutor(persona.getNombreUsuario());

        List<Publicacion> publicaciones = persona.getPublicaciones();

        publicaciones.add(publicacion);

        persona.setPublicaciones(publicaciones);

        Persona personaGuardada = personaRepo.save(persona);
        
        Publicacion publicacionGuardada = Collections.max(personaGuardada.getPublicaciones(), Comparator.comparingLong(Publicacion::getId));
        
        System.out.println("LLEGUE ACA 7");
        return publicacionGuardada;
    }

    @Override
    public void eliminarPublicacion(int idPublicacion) {
        publicacionRepository.deleteById(idPublicacion);
    }

    @Override
    public Publicacion editarPublicacion(int idPublicacion, PublicacionRequest publicacionRequest) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion).orElse(null);
        
        if(publicacionRequest.getTitulo() != null) 
            publicacion.setTitulo(publicacionRequest.getTitulo());
        if(publicacionRequest.getContenido() != null) 
            publicacion.setContenido(publicacionRequest.getContenido());
        if(publicacionRequest.getTema() != null) 
            publicacion.setTema(publicacionRequest.getTema());
        if(publicacionRequest.getFecha() != null) 
            publicacion.setFecha(publicacionRequest.getFecha());
        
        return publicacionRepository.save(publicacion);
    }
    
}
