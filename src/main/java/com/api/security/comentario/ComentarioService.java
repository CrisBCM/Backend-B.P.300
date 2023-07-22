package com.api.security.comentario;

import com.api.security.persona.Persona;
import com.api.security.persona.PersonaRepository;
import com.api.security.publicacion.Publicacion;
import com.api.security.publicacion.PublicacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService implements IComentarioService{
    
    @Autowired
    private PublicacionRepository publicacionRepo;
    
    @Autowired
    private ComentarioRepository comentarioRepo;
    
    @Autowired
    private PersonaRepository personaRepo;
    
    @Override
    public Comentario añadirComentario(int idPublicacion, int idPersona, ComentarioDto comentario) {
        
        Publicacion publicacion = publicacionRepo.findById(idPublicacion).orElse(null);
        
        Persona persona = personaRepo.findById(idPersona).orElse(null);
        
        Comentario comentarioNuevo = 
                Comentario.builder()
                .contenido(comentario.getContenido())
                .fecha(comentario.getFecha())
                .autor(persona.getNombreUsuario())
                .persona(persona)
                .publicacion(publicacion)
                .build();

        return comentarioRepo.save(comentarioNuevo);
    }
    
}
