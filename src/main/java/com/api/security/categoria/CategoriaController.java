package com.api.security.categoria;

import com.api.security.dto.CategoriaDTO;
import com.api.security.dto.CategoriaResumenDTO;
import com.api.security.dto.PublicacionDTO;
import com.api.security.usuario.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/todas")
    public List<CategoriaDTO> getCategorias()
    {
        return categoriaService.obtenerCategorias();
    }
    @GetMapping("/{nombre}")
    public Set<PublicacionDTO> getPublicacionesDeCategoria(@PathVariable String nombre)
    {
        return categoriaService.getPublicacionesDeCategoria(nombre);
    }
    @GetMapping("/resumen")
    public List<CategoriaResumenDTO> getCategoriasResumen()
    {
        return categoriaService.obtenerResumenCategorias();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Object> crearCategoria(@RequestBody CategoriaRequest categoriaRequest)
    {
        if(categoriaService.existsByNombre(categoriaRequest.getNombre()))
            return new ResponseEntity<>("El nombre de la categoria ya existe", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(categoriaService.crearCategoria(categoriaRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("eliminar/{id}")
    public void eliminarCategoria(@PathVariable int id){
        categoriaService.eliminarCategoriaPorId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public void editarCategoria(@PathVariable int id,
                                                     @RequestParam ("nuevoNombre") String nuevoNombre,
                                                     @RequestParam ("nuevaDescripcion") String nuevaDescripcion)
    {
        categoriaService.editarCategoria(id, nuevoNombre, nuevaDescripcion);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cambiarestado/{id}")
    public void cambiarEstado(@PathVariable int id){
        categoriaService.habilitarODeshabilitarCategoria(id);
    }
}
