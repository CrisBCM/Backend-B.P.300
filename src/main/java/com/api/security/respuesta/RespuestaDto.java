package com.api.security.respuesta;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDto {
    private String contenido;
    private LocalDateTime fecha;
}
