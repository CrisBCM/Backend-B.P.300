package com.api.security.publicacion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    
    @Autowired
    private IPublicacionService publicacionService;
    
    @GetMapping("/todas")
    public List<Publicacion> obtenerPublicaciones(){
        return publicacionService.obtenerPublicaciones();
    }
    @PostMapping("/añadir/{idPersona}")
    public ResponseEntity<Publicacion> añadirPublicacion(@PathVariable int idPersona, @RequestBody PublicacionRequest requestPublicacion){
        
        System.out.println("REQUEST PUBLICACION : " + requestPublicacion);
       Publicacion nuevaPublicacion = publicacionService.añadirPublicacion(idPersona, requestPublicacion);
       
       return ResponseEntity.ok(nuevaPublicacion);
    }
    @DeleteMapping("/eliminar/{idPublicacion}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable int idPublicacion){
        publicacionService.eliminarPublicacion(idPublicacion);
        
        return ResponseEntity.ok("Publicacion eliminada correctamente");
    }
    @PutMapping("/editar/{idPublicacion}")
    public Publicacion editarPublicacion(@PathVariable int idPublicacion, @RequestBody PublicacionRequest publicacion){
        
        return publicacionService.editarPublicacion(idPublicacion, publicacion);
    }
}
