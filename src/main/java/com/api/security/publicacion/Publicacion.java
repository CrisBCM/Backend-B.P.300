package com.api.security.publicacion;

import com.api.security.categoria.Categoria;
import com.api.security.comentario.Comentario;
import com.api.security.persona.Persona;
import com.api.security.shared.Tema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private LocalDateTime fecha;
    private String autor;
    private String fotoAutor;
    @ElementCollection
    @CollectionTable(name="lista_me_gusta_publicacion", joinColumns = @JoinColumn(name = "publicacion_id"))
    @Column(name = "nombreUsuario", length = 50)
    @Builder.Default
    private Set<String> listaMeGusta= new HashSet<>();
    @ElementCollection
    @CollectionTable(name="lista_no_me_gusta_publicacion", joinColumns = @JoinColumn(name = "publicacion_id"))
    @Column(name = "nombreUsuario", length = 50)
    @Builder.Default
    private Set<String> listaNoMeGusta = new HashSet<>();

    private int puntuacion;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_persona")
    private Persona persona;
    
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @Builder.Default
    private List<Comentario> comentarios = new ArrayList<>();

    public void addMeGusta(String nombreUsuario){

        if(!listaMeGusta.contains(nombreUsuario)){

            if(listaNoMeGusta.contains(nombreUsuario)){
                listaNoMeGusta.remove(nombreUsuario);
                puntuacion++;
            }

            listaMeGusta.add(nombreUsuario);
            puntuacion++;

        }else{
            listaMeGusta.remove(nombreUsuario);
            puntuacion--;
        }
        recalcularPuntuacion();
    }

    public void addNoMeGusta(String nombreUsuario){

        if(!listaNoMeGusta.contains(nombreUsuario)){

            if(listaMeGusta.contains(nombreUsuario)){
                listaMeGusta.remove(nombreUsuario);
            }
            listaNoMeGusta.add(nombreUsuario);
        }else{
            listaNoMeGusta.remove(nombreUsuario);
        }
        recalcularPuntuacion();
    }
    public void recalcularPuntuacion(){
        puntuacion = listaMeGusta.size() - listaNoMeGusta.size();
    }
}