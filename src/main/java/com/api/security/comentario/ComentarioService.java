package com.api.security.comentario;

import com.api.security.Constantes;
import com.api.security.persona.Persona;
import com.api.security.persona.PersonaRepository;
import com.api.security.publicacion.Publicacion;
import com.api.security.publicacion.PublicacionRepository;
import java.time.LocalDateTime;
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
    public Comentario añadirComentario(int idPublicacion, int idPersona, String contenido) {
        
        Publicacion publicacion = publicacionRepo.findById(idPublicacion).orElse(null);
        
        Persona persona = personaRepo.findById(idPersona).orElse(null);
        Comentario comentarioNuevo = 
                Comentario.builder()
                .contenido(contenido)
                .fecha(LocalDateTime.now(Constantes.ZONA_HORARIA_ARGENTINA))
                .autor(persona.getNombreUsuario())
                .fotoAutor(persona.getImgAvatar().getPath())
                .persona(persona)
                .publicacion(publicacion)
                .build();

        return comentarioRepo.save(comentarioNuevo);
    }

    @Override
    public List<Comentario> cambiarFotoComentario(Persona persona) {
        List<Comentario> listaComentarios = persona.getComentarios();
        
        for(Comentario comentario : listaComentarios){
            comentario.setFotoAutor(persona.getImgAvatar().getPath());
        }
        
        return listaComentarios;
    }

    @Override
    public void meGusta(int idComentario, String nombreUsuario) {
        Comentario comentario = comentarioRepo.findById(idComentario).orElse(null);
        
        comentario.setMeGusta(nombreUsuario);
        
        comentarioRepo.save(comentario);
    }

    @Override
    public void noMeGusta(int idComentario, String nombreUsuario) {
        Comentario comentario = comentarioRepo.findById(idComentario).orElse(null);
        
        comentario.setNoMeGusta(nombreUsuario);
        
        comentarioRepo.save(comentario);
    }

    @Override
    public void eliminarComentario(int idComentario) {
        System.out.println("eliminando comentario");
        comentarioRepo.deleteById(idComentario);
        System.out.println("elimine comentario");
    }

    @Override
    public Comentario editarComentario(int idComentario, String nuevoContenido) {
        Comentario comentario = comentarioRepo.findById(idComentario).orElse(null);
        
        comentario.setContenido(nuevoContenido);
        
        return comentarioRepo.save(comentario);
    }
    
}
