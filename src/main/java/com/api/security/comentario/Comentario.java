package com.api.security.comentario;

import com.api.security.persona.Persona;
import com.api.security.publicacion.Publicacion;
import com.api.security.respuesta.Respuesta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private int id;
    
    private String autor;
    private String fotoAutor;
    private String contenido;
    private LocalDateTime fecha;
    
    @ElementCollection
    @CollectionTable(name = "lista_me_gusta", joinColumns = @JoinColumn(name = "comentario_id"))
    @Column(name = "nombreUsuario", length = 50)
    @Builder.Default
    private Set<String> listaMeGusta = new HashSet<>();
    
    @ElementCollection
    @CollectionTable(name = "lista_no_me_gusta", joinColumns = @JoinColumn(name = "comentario_id"))
    @Column(name = "nombreUsuario", length = 50)
    @Builder.Default
    private Set<String> listaNoMeGusta = new HashSet<>();
    
    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Builder.Default
    private List<Respuesta> respuestas = new ArrayList<>();
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_persona")
    private Persona persona;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_publicacion")
    private Publicacion publicacion;
    
    public void setMeGusta(String nombreUsuario){
        if(listaNoMeGusta.contains(nombreUsuario)) listaNoMeGusta.remove(nombreUsuario);
        
        if(listaMeGusta.contains(nombreUsuario)){
            listaMeGusta.remove(nombreUsuario);
        }
        else
        {
            listaMeGusta.add(nombreUsuario);
        }
    }
    
    public void setNoMeGusta(String nombreUsuario){
        if(listaMeGusta.contains(nombreUsuario)) listaMeGusta.remove(nombreUsuario);
        
        if(listaNoMeGusta.contains(nombreUsuario)){
            listaNoMeGusta.remove(nombreUsuario);
        }
        else
        {
            listaNoMeGusta.add(nombreUsuario);
        }
    }
}
