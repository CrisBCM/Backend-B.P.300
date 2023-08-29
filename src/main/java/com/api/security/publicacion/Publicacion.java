package com.api.security.publicacion;

import com.api.security.comentario.Comentario;
import com.api.security.persona.Persona;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Publicacion {
    @Id
    @GeneratedValue
    private int id;
    private String titulo;
    
    @Column(length = 3000)
    private String contenido;
    
    private String tema;
    private LocalDateTime fecha;
    private String autor;
    private String fotoAutor;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_persona")
    private Persona persona;
    
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Builder.Default
    private List<Comentario> comentarios = new ArrayList<>();
}
