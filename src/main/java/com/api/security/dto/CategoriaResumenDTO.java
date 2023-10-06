package com.api.security.dto;

import com.api.security.categoria.Categoria;
import lombok.Getter;

@Getter
public class CategoriaResumenDTO {
    private int id;
    private String nombre;

    public CategoriaResumenDTO (Categoria categoria){
        id = categoria.getId();
        nombre = categoria.getNombre();
    }
}
