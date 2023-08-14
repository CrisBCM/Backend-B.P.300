package com.api.security.persona;

import com.api.security.imagen.Imagen;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persona")
public class PersonaController {
    
    private final IPersonaService personaService;
    private final HttpServletRequest request;
    
    @GetMapping("/obtener/{id}")
    public Persona obtenerPersona(@PathVariable int id){
        return personaService.obtenerPersona(id);
    }
    @PostMapping("/avatar/cambiar/{nombreUsuario}")
    public ResponseEntity<Imagen> cambiarImagen(@PathVariable String nombreUsuario,
                                                @RequestParam ("imagen") MultipartFile file)
    {
        Imagen nuevaImagenAvatar = personaService.cambiarImagen(nombreUsuario, file, request);
        
        return ResponseEntity.ok(nuevaImagenAvatar);
    }
}
