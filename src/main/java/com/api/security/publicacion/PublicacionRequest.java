package com.api.security.publicacion;

import java.time.LocalDateTime;

import com.api.security.shared.Tema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionRequest {
    private String titulo;
    private String contenido;
    private String categoria;
}
