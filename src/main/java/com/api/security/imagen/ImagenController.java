package com.api.security.imagen;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagen")
public class ImagenController {
    
     @Autowired
     private ImagenService imagenService;
     
     @GetMapping("/obtener/{nombreFile:.+}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreFile) throws IOException
    {
        Resource file = imagenService.cargarResource(nombreFile);
        String contentType = Files.probeContentType(file.getFile().toPath());
        
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
}
