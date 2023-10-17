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
    private int cantidadPublicaciones;
    private UltimaPublicacionDTO ultimaPublicacion;
    private boolean habilitado;

    public CategoriaDTO (Categoria categoria){
        id = categoria.getId();
        nombre = categoria.getNombre();
        descripcion = categoria.getDescripcion();
        cantidadPublicaciones = categoria.getCantidadPublicaciones();
        habilitado = categoria.isHabilitado();
        if(!categoria.getPublicaciones().isEmpty()) ultimaPublicacion = new UltimaPublicacionDTO(categoria.getPublicaciones()
                .stream()
                .max((a,b) -> a.getFecha().compareTo(b.getFecha()))
                .orElse(null));
    }
}
