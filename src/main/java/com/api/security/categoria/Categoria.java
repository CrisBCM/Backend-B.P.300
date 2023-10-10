package com.api.security.categoria;

import com.api.security.publicacion.Publicacion;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private int id;

    private String nombre;
    private String descripcion;
    private boolean habilitado;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private Set<Publicacion> publicaciones;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPublicaciones(Set<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void addPublicacion(Publicacion publicacion){
        publicacion.setCategoria(this);
        publicaciones.add(publicacion);
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
