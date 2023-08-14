package com.api.security.comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;
    
    @PostMapping("/añadir/{idPublicacion}/{idAutor}")
    public ResponseEntity<Comentario> añadirComentario(@PathVariable int idPublicacion,
                                                       @PathVariable int idAutor,
                                                       @RequestParam ("contenido") String contenido)
    {
        
        Comentario comentarioNuevo = comentarioService.añadirComentario(idPublicacion, idAutor, contenido);
        
        return ResponseEntity.ok(comentarioNuevo);
    }
    @DeleteMapping("/eliminar/{idComentario}")
    public void eliminarComentario(@PathVariable int idComentario)
    {
        comentarioService.eliminarComentario(idComentario);
    }
    
    @PutMapping("/editar/{idComentario}")
    public ResponseEntity<Comentario> editarComentario(@PathVariable int idComentario,
                                                       @RequestParam ("contenido") String contenido)
    {
        Comentario comentario = comentarioService.editarComentario(idComentario, contenido);
        
        return ResponseEntity.ok(comentario);
    }
    
    @PutMapping("/megusta/{idComentario}/{nombreUsuario}")
    public void meGusta(@PathVariable int idComentario,
                        @PathVariable String nombreUsuario)
    {
        comentarioService.meGusta(idComentario, nombreUsuario);
    }
    @PutMapping("/nomegusta/{idComentario}/{nombreUsuario}")
      public void noMeGusta(@PathVariable int idComentario,
                          @PathVariable String nombreUsuario)
      {
          comentarioService.noMeGusta(idComentario, nombreUsuario);
      }
}
