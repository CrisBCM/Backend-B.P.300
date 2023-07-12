package com.api.security.imagen;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface IImagenService {
  public void init() throws IOException;
  public Imagen guardarImagen(String nombreUsuario, MultipartFile file, HttpServletRequest request);
  public String obtenerUrlImagen(HttpServletRequest request, String fileName);
  public Resource cargarResource(String nombreFile);
  public void eliminarResource(String nombreFile);
}
