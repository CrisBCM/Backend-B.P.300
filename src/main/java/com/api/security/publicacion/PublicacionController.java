package com.api.security.publicacion;

import com.api.security.dto.PublicacionDTO;
import java.util.List;
import java.util.stream.Collectors;

import com.api.security.dto.PublicacionRequestDTO;
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
    public List<PublicacionDTO> obtenerPublicaciones(){
        return publicacionService.obtenerPublicaciones()
                                    .stream()
                                    .map(publicacion -> new PublicacionDTO(publicacion))
                                    .collect(Collectors.toList());
    }
    @PostMapping("/añadir/{idPersona}")
    public ResponseEntity<PublicacionDTO> añadirPublicacion(@PathVariable int idPersona, @RequestBody PublicacionRequest requestPublicacion){
        
        System.out.println("REQUEST PUBLICACION : " + requestPublicacion);
       Publicacion nuevaPublicacion = publicacionService.añadirPublicacion(idPersona, requestPublicacion);
       
       return ResponseEntity.ok(new PublicacionDTO(nuevaPublicacion));
    }
    @DeleteMapping("/eliminar/{idPublicacion}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable int idPublicacion){
        publicacionService.eliminarPublicacion(idPublicacion);
        
        return ResponseEntity.ok("Publicacion eliminada correctamente");
    }
    @PutMapping("/editar/{idPublicacion}")
    public PublicacionDTO editarPublicacion(@PathVariable int idPublicacion, @RequestBody PublicacionRequestDTO publicacion){
        
        return new PublicacionDTO(publicacionService.editarPublicacion(idPublicacion, publicacion));
    }

    @PutMapping("/megusta/{publicacionId}/{nombreUsuario}")
    public void meGusta(@PathVariable int publicacionId,
                        @PathVariable String nombreUsuario)
    {
        publicacionService.meGusta(publicacionId, nombreUsuario);
    }
    @PutMapping("/nomegusta/{publicacionId}/{nombreUsuario}")
    public void noMeGusta(@PathVariable int publicacionId,
                        @PathVariable String nombreUsuario)
    {
        publicacionService.noMeGusta(publicacionId, nombreUsuario);
    }
}
