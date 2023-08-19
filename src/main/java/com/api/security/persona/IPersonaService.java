package com.api.security.persona;

import com.api.security.imagen.Imagen;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;


public interface IPersonaService {
    public Persona obtenerPersona(int id);
    public Imagen cambiarImagen(String nombreUsuario, MultipartFile file, HttpServletRequest request);
    public Persona obtenerUsuario(String nombreUsuario);
}
