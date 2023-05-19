package com.api.security.persona;

import com.api.security.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    
    private String nombre;
    private String apellido;
    private int pesoCorporal;
    private int cantidadActividad;
    
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
