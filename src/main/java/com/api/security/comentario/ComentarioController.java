package com.api.security.comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;
    
    @PostMapping("/añadir/{idPublicacion}/{idAutor}")
    public ResponseEntity<Comentario> añadirComentario(@PathVariable int idPublicacion,
                                                       @PathVariable int idAutor,
                                                       @RequestBody ComentarioDto comentario)
    {
        Comentario comentarioNuevo = comentarioService.añadirComentario(idPublicacion, idAutor, comentario);
        
        return ResponseEntity.ok(comentarioNuevo);
    }
    
}
