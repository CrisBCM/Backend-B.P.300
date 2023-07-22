package com.api.security.auth;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String nombreCompleto;
    private String nombreUsuario;
    private String sexo;
    private int pesoCorporal;
    private double cantidadActividad;
}
