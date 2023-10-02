package com.api.security.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/todas")
    public List<Categoria> getCategorias(Authentication authentication)
    {
        System.out.println(authentication.getAuthorities() + " AUTORITHIES DE JUANNNNN");
        return categoriaService.obtenerCategorias();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody CategoriaRequest categoriaRequest)
    {
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
}
