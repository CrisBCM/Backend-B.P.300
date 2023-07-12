package com.api.security.comida;

import com.api.security.estomago.Estomago;
import com.api.security.imagen.Imagen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Comida {
    @Id
    @GeneratedValue
    private int id;
    private String nombreComida;
    private int calorias;
    
    @OneToOne(mappedBy = "comida", cascade = CascadeType.ALL)
    private Imagen imagen;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_estomago")
    private Estomago estomago;
}
