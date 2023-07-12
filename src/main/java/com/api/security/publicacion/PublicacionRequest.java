package com.api.security.publicacion;

import java.time.LocalDateTime;
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
    private String tema;
    private LocalDateTime fecha;
}
