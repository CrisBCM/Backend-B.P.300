package com.api.security.comentario;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDto {
    private String contenido;
    private LocalDateTime fecha;
}
