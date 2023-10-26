package com.api.security.respuesta;

import com.api.security.Constantes;
import com.api.security.comentario.Comentario;
import com.api.security.comentario.ComentarioRepository;
import com.api.security.persona.Persona;
import com.api.security.persona.PersonaRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService implements IRespuestaService{
    
    
    @Autowired
    private IRespuestaRepository respuestaRepo;
    @Autowired
    private ComentarioRepository comentarioRepo;
    @Autowired
    private PersonaRepository personaRepo;
    
    @Override
    public Respuesta añadirRespuesta(int idComentario, int idPersona, String contenido) {
        
        Comentario comentario = comentarioRepo.findById(idComentario).orElse(null);
        
        Persona persona = personaRepo.findById(idPersona).orElse(null);
        
        Respuesta nuevaRespuesta = Respuesta.builder()
                                   .autor(persona.getNombreUsuario())
                                   .fotoAutor(persona.getImgAvatar().getPath())
                                   .contenido(contenido)
                                   .fecha(LocalDateTime.now(Constantes.ZONA_HORARIA_ARGENTINA))
                                   .persona(persona)
                                   .comentario(comentario)
                                   .build();

        return respuestaRepo.save(nuevaRespuesta);
    }

    @Override
    public void eliminarRespuesta(int idRespuesta) {
        respuestaRepo.deleteById(idRespuesta);
    }

    @Override
    public Respuesta editarRespuesta(int idRespuesta, String contenido) {
        Respuesta respuesta = respuestaRepo.findById(idRespuesta).orElse(null);
        respuesta.setContenido(contenido);
        
        return respuestaRepo.save(respuesta);
    }

    @Override
    public void meGusta(String nombreUsuario, int idRespuesta) {
        Respuesta respuesta = respuestaRepo.findById(idRespuesta).orElse(null);
        
        respuesta.addListaMeGusta(nombreUsuario);
        
        respuestaRepo.save(respuesta);
    }

    @Override
    public void noMeGusta(String nombreUsuario, int idRespuesta) {
        Respuesta respuesta = respuestaRepo.findById(idRespuesta).orElse(null);
        
        respuesta.addListaNoMeGusta(nombreUsuario);
        
        respuestaRepo.save(respuesta);
    }
    
}
