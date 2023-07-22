
package com.api.security.persona;

import com.api.security.imagen.Imagen;
import com.api.security.imagen.ImagenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    private PersonaRepository personaRepo;
    @Autowired
    private ImagenService imgService;

    @Override
    public Persona obtenerPersona(int id) {
        return personaRepo.findById(id).orElse(null);
    }

    @Override
    public Imagen cambiarImagen(String nombreUsuario, MultipartFile file, HttpServletRequest request) {
        Persona persona = personaRepo.findByNombreUsuario(nombreUsuario);
        Imagen nuevaImagenAvatar = imgService.guardarImagen(nombreUsuario, file, request);
        
        String nombreDeImagenAnterior = persona.getImgAvatar().getNombre();
        if(!"avatar1.jpg".equals(nombreDeImagenAnterior) && nombreDeImagenAnterior != nuevaImagenAvatar.getNombre()){
            imgService.eliminarResource(persona.getImgAvatar().getNombre());
        }
        
        persona.setImgAvatar(nuevaImagenAvatar);
        Persona personaEditada = personaRepo.save(persona);
        
        return personaEditada.getImgAvatar();
    }

    
}
