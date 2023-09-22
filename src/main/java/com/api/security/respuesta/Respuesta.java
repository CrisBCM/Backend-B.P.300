package com.api.security.respuesta;

import com.api.security.comentario.Comentario;
import com.api.security.persona.Persona;
import com.api.security.publicacion.Publicacion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Respuesta {
    @Id
    @GeneratedValue
    private int id;
    
    private String autor;
    private String fotoAutor;
    private String contenido;
    private LocalDateTime fecha;
    
    @ElementCollection
    @CollectionTable(name = "lista_me_gusta_resp", joinColumns = @JoinColumn(name = "respuesta_id"))
    @Column(name = "nombreUsuario", length = 50)
    @Builder.Default
    private Set<String> listaMeGusta = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "lista_no_me_gusta_resp", joinColumns = @JoinColumn(name = "respuesta_id"))
    @Column(name = "nombreUsuario", length = 50)
    @Builder.Default
    private Set<String> listaNoMeGusta = new HashSet<>();
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_persona")
    private Persona persona;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_comentario")
    private Comentario comentario;
    
    public void addListaMeGusta(String nombreUsuario){
        if(!listaMeGusta.contains(nombreUsuario)){

            if(listaNoMeGusta.contains(nombreUsuario)) listaNoMeGusta.remove(nombreUsuario);

            listaMeGusta.add(nombreUsuario);
        }else{
            listaMeGusta.remove(nombreUsuario);
        }
    }
    
    public void addListaNoMeGusta(String nombreUsuario){
        if(!listaNoMeGusta.contains(nombreUsuario)){

            if(listaMeGusta.contains(nombreUsuario)) listaMeGusta.remove(nombreUsuario);

            listaNoMeGusta.add(nombreUsuario);
        }else{
            listaNoMeGusta.remove(nombreUsuario);
        }
    }
    
}
