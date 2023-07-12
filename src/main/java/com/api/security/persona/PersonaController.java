package com.api.security.persona;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/persona")
public class PersonaController {
    
    private final IPersonaService personaService;
    
    @GetMapping("/obtener/{id}")
    public Persona obtenerPersona(@PathVariable int id){
        return personaService.obtenerPersona(id);
    }
}
