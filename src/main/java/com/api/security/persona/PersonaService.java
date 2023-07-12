
package com.api.security.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    private PersonaRepository personaRepo;

    @Override
    public Persona obtenerPersona(int id) {
        return personaRepo.findById(id).orElse(null);
    }

    
}
