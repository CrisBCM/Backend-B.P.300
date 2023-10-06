package com.api.security.dto;

import com.api.security.categoria.Categoria;
import com.api.security.publicacion.Publicacion;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CategoriaDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private Set<PublicacionDTO> publicaciones = new HashSet<>();

    public CategoriaDTO (Categoria categoria){
        id = categoria.getId();
        nombre = categoria.getNombre();
        descripcion = categoria.getDescripcion();
        publicaciones = categoria.getPublicaciones()
                .stream()
                .map(PublicacionDTO::new)
                .collect(Collectors.toSet());
    }
}
