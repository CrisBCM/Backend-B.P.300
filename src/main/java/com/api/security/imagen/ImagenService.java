package com.api.security.imagen;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ImagenService implements IImagenService{
    
    @Value("${ruta.imagenes}")
    private String rutaImagenes;
   
    private Path rootLocation;
    
    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(rutaImagenes);
        
        Files.createDirectories(rootLocation);
    }

    @Override
    public Imagen guardarImagen(String nombreUsuario, MultipartFile file, HttpServletRequest request){

        try {
            if(file.isEmpty()) throw new RuntimeException("La imagen esta vacia o corrupta!.");
        
        String nombreFile = nombreUsuario + file.getOriginalFilename();
        
        Path destinationFile = rootLocation.resolve(Paths.get(nombreFile))
                .normalize()
                .toAbsolutePath();

        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        
        
        String url = obtenerUrlImagen(request, nombreFile);
        
        Imagen imagen = Imagen.builder()
                .nombre(nombreFile)
                .path(url)
                .build();
        
        
        return imagen;
        
        } catch (IOException e) {
            throw new RuntimeException("Falló en guardar la imagen!.");
        }
 
    }

    @Override
    public String obtenerUrlImagen(HttpServletRequest request, String fileName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/v3/comida/")
                .path(fileName)
                .toUriString();
        
        return url;
    }

    @Override
    public Resource cargarResource(String nombreFile) {
        try{
            Path file = rootLocation.resolve(nombreFile);
            Resource resource = (Resource) new UrlResource((file.toUri()));
            
            if (resource.exists() || resource.isReadable()){
                
                return resource;
                
            }else{
                throw new RuntimeException("La imagen no existe" + nombreFile);
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("No se pudo leer la imagen" + nombreFile);
        }
    }

    @Override
    public void eliminarResource(String nombreFile) {
        Path file = rootLocation.resolve(nombreFile);
        File imagen = new File(file.toString());
        
        if(imagen.exists()){
            imagen.delete();
        }
    }
  
}
