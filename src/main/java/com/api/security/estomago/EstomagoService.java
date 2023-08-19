package com.api.security.estomago;

import com.api.security.comida.Comida;
import com.api.security.comida.ComidaRepository;
import com.api.security.imagen.IImagenService;
import com.api.security.imagen.Imagen;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EstomagoService implements IEstomagoService{
    
    @Autowired
    private EstomagoRepository estomagoRepo;
    
    @Autowired
    private IImagenService imagenService;
    
    @Autowired
    private ComidaRepository comidaRepo;
   

    @Override
    public Comida añadirComida(String nombreUsuario ,int id, MultipartFile file, String nombreComida, int calorias, HttpServletRequest request) {

        Estomago estomago = estomagoRepo.findById(id).orElse(null);

        Imagen imagen = imagenService.guardarImagen(nombreUsuario, file, request);

        Comida com = Comida.builder()
                .nombreComida(nombreComida)
                .calorias(calorias)
                .build();
        
        
        com.setEstomago(estomago);
        
        imagen.setComida(com);
        
        com.setImagen(imagen);
        
        List<Comida> listaComida = (List<Comida>) estomago.getComidas();
        
        listaComida.add(com);
        
        estomago.setComidas(listaComida);
        
        Estomago estomagoGuardado = estomagoRepo.save(estomago);
        
        Comida comida = Collections.max(estomagoGuardado.getComidas(), Comparator.comparingLong(Comida::getId));
        
        return comida;
    }

    @Override
    public void eliminarComida(int idEstomago, int idComida) {
        
       Estomago estomago = estomagoRepo.findById(idEstomago).orElse(null);
       
       Comida comida = comidaRepo.findById(idComida).orElse(null);
       
       Imagen imagen = comida.getImagen();
       
       var filename = imagen.getNombre();
       
       imagenService.eliminarResource(filename);
       
       List<Comida> listaComidas = estomago.getComidas();
       
       if(!listaComidas.isEmpty()){
           
           listaComidas.remove(comida);
           
           estomago.setComidas(listaComidas);
           
           estomagoRepo.save(estomago);

           comidaRepo.delete(comida);
       }
       
        
    }

    @Override
    public Comida editarComida(int idEstomago, int idComida, String nuevoNombreComida, int nuevoCalorias, String nombreUsuario, MultipartFile file, HttpServletRequest request) {
        Comida comida = comidaRepo.findById(idComida).orElse(null);
        
       if(file != null){
           Imagen imagen = comida.getImagen();
           
           String fileName = imagen.getNombre();
           
           imagenService.eliminarResource(fileName);
           
           Imagen nuevaImagen = imagenService.guardarImagen(nombreUsuario, file, request);

           
           imagen.setNombre(nuevaImagen.getNombre());
           imagen.setPath(nuevaImagen.getPath());
           
           comida.setImagen(imagen);
       }
        
        comida.setNombreComida(nuevoNombreComida);
        comida.setCalorias(nuevoCalorias);
        
        Comida savedComida = comidaRepo.save(comida);
         
        return savedComida;
    }

    
    
}
