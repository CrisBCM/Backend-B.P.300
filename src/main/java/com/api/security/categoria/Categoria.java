package com.api.security.categoria;

import com.api.security.publicacion.Publicacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private int id;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    private Set<Publicacion> publicaciones = new HashSet<>();
}
