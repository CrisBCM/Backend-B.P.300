package com.api.security.estomago;

import com.api.security.comida.Comida;
import com.api.security.imagen.IImagenService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v3/comida")
@RequiredArgsConstructor
public class EstomagoController {
    
    private final IEstomagoService estomagoService;
    private final IImagenService imagenService;
    private final HttpServletRequest request;
    
    @PutMapping(value = "/añadir/{id}/{nombreUsuario}", consumes = { "multipart/form-data" })
    public ResponseEntity<Comida> añadirComida(@PathVariable int id,
                               @PathVariable String nombreUsuario,
                               @RequestParam ("nombreComida") String nombreComida,
                               @RequestParam("calorias") int calorias,
                               @RequestParam ("imagen") MultipartFile file)
    {   
        
        Comida comida = estomagoService.añadirComida(nombreUsuario,id, file, nombreComida, calorias, request);
        
        return ResponseEntity.ok(comida);
    }
    
    @DeleteMapping("/eliminar/{idEstomago}/{idComida}")
    public ResponseEntity<String> eliminarComida(@PathVariable int idEstomago,
                                                 @PathVariable int idComida)
    {
        estomagoService.eliminarComida(idEstomago, idComida);
         
        return ResponseEntity.ok("La comida fue eliminada correctamente.");
    }
    
//    @GetMapping("/{nombreFile:.+}")
//    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreFile) throws IOException
//    {
//        Resource file = imagenService.cargarResource(nombreFile);
//        String contentType = Files.probeContentType(file.getFile().toPath());
//        
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_TYPE, contentType)
//                .body(file);
//    }
    @PutMapping(value = "/editar/{idEstomago}/{idComida}/{nombreUsuario}", consumes = { "multipart/form-data" })
    public ResponseEntity<Comida> editarComida(@PathVariable int idEstomago,
                                               @PathVariable int idComida,
                                               @PathVariable String nombreUsuario,
                                               @RequestParam ("nombreComida") String nombreComida,
                                               @RequestParam ("calorias") int calorias,
                                               @RequestParam (name = "imagen", required = false) MultipartFile file)
    {
        
        
        Comida comida = estomagoService.editarComida(idEstomago, idComida, nombreComida, calorias, nombreUsuario, file, request);
        
        return ResponseEntity
                .ok()
                .body(comida);
    }
    
}
