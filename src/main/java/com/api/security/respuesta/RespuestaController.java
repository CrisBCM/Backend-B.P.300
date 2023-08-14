package com.api.security.respuesta;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    private IRespuestaService respuestaService;
    
    @PostMapping("/añadir/{idComentario}/{idPersona}")
    public ResponseEntity<Respuesta> añadirRespuesta(@PathVariable int idComentario,
                                                     @PathVariable int idPersona,
                                                     @RequestParam ("contenido") String contenido)
    {
       Respuesta respuestaGuardada = respuestaService.añadirRespuesta(idComentario, idPersona, contenido);
       
       return ResponseEntity.ok(respuestaGuardada);
    }
    @DeleteMapping("/eliminar/{idRespuesta}")
    public String eliminarRespuesta(@PathVariable int idRespuesta){
        respuestaService.eliminarRespuesta(idRespuesta);
        
        return "Respuesta eliminada!";
    }
    @PutMapping("/editar/{idRespuesta}")
    public ResponseEntity<Respuesta> editarRespuesta(@PathVariable int idRespuesta,
                                                     @RequestParam ("contenido") String contenido)
    {
        Respuesta respuesta = respuestaService.editarRespuesta(idRespuesta, contenido);
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PutMapping("/megusta/{idRespuesta}/{nombreUsuario}")
    public void meGusta(@PathVariable int idRespuesta,
                        @PathVariable String nombreUsuario)
    {
        respuestaService.meGusta(nombreUsuario, idRespuesta);
    }
    @PutMapping("/nomegusta/{idRespuesta}/{nombreUsuario}")
    public void noMeGusta(@PathVariable int idRespuesta,
                        @PathVariable String nombreUsuario)
    {
        respuestaService.noMeGusta(nombreUsuario, idRespuesta);
    }
}
