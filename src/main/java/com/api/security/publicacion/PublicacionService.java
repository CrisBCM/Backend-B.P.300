package com.api.security.publicacion;

import com.api.security.categoria.Categoria;
import com.api.security.categoria.CategoriaRepository;
import com.api.security.comida.Comida;
import com.api.security.dto.PublicacionRequestDTO;
import com.api.security.persona.Persona;
import com.api.security.persona.PersonaRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService implements IPublicacionService{
    
    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    PersonaRepository personaRepo;
    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Override
    public List<Publicacion> obtenerPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public Publicacion añadirPublicacion(int idPersona, PublicacionRequest requestPublicacion) {
        
        Persona persona = personaRepo.findById(idPersona).orElse(null);
        Categoria categoria = categoriaRepository.findByNombre(requestPublicacion.getCategoria());
        
        Publicacion publicacion = Publicacion.builder()
                                    .autor(persona.getNombreUsuario())
                                    .categoria(categoria)
                                    .titulo(requestPublicacion.getTitulo())
                                    .contenido(requestPublicacion.getContenido())
                                    .puntuacion(0)
                                    .fotoAutor(persona.getImgAvatar().getPath())
                                    .fecha(LocalDateTime.now())
                                    .persona(persona)
                                    .build();

        List<Publicacion> publicaciones = persona.getPublicaciones();

        publicaciones.add(publicacion);

        persona.setPublicaciones(publicaciones);

        Persona personaGuardada = personaRepo.save(persona);
        
        Publicacion publicacionGuardada = Collections.max(personaGuardada.getPublicaciones(), Comparator.comparingLong(Publicacion::getId));
        return publicacionGuardada;
    }

    @Override
    public void eliminarPublicacion(int idPublicacion) {
        publicacionRepository.deleteById(idPublicacion);
    }

    @Override
    public Publicacion editarPublicacion(int idPublicacion, PublicacionRequestDTO publicacionRequest) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion).orElse(null);
        
        if(publicacionRequest.getTitulo() != null) 
            publicacion.setTitulo(publicacionRequest.getTitulo());

        if(publicacionRequest.getContenido() != null) 
            publicacion.setContenido(publicacionRequest.getContenido());
        
        return publicacionRepository.save(publicacion);
    }

    @Override
    public List<Publicacion> actualizarFotoPublicacion(Persona persona) {
        List<Publicacion> listaPublicaciones = persona.getPublicaciones();

        for(Publicacion publicacion : listaPublicaciones){
            publicacion.setFotoAutor(persona.getImgAvatar().getPath());
        }

        return listaPublicaciones;
    }

    @Override
    public void meGusta(int publicacionId, String nombreUsuario) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElse(null);
        publicacion.addMeGusta(nombreUsuario);
        publicacionRepository.save(publicacion);
    }

    @Override
    public void noMeGusta(int publicacionId, String nombreUsuario) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElse(null);
        publicacion.addNoMeGusta(nombreUsuario);
        publicacionRepository.save(publicacion);
    }

}
