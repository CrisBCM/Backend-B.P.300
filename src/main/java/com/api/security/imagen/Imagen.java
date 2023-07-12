package com.api.security.imagen;

import com.api.security.comida.Comida;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Imagen {
    @Id
    @GeneratedValue
    private int id;
    
    private String nombre;
    private String path;
    
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_comida")
    private Comida comida;

    public Imagen(String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
    }

    
}
