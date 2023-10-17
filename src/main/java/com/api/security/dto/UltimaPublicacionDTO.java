package com.api.security.dto;

import com.api.security.publicacion.Publicacion;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UltimaPublicacionDTO {
    private String autor;
    private LocalDateTime fecha;

    public UltimaPublicacionDTO(Publicacion publicacion){
        autor = publicacion.getAutor();
        fecha = publicacion.getFecha();
    }
}
