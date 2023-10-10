package com.api.security.dto;

import com.api.security.categoria.Categoria;
import lombok.Getter;

@Getter
public class CategoriaResumenDTO {
    private String nombre;
    private boolean habilitado;

    public CategoriaResumenDTO (Categoria categoria){
        nombre = categoria.getNombre();
        habilitado = categoria.isHabilitado();
    }
}
