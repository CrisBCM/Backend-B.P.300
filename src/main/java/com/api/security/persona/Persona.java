package com.api.security.persona;

import com.api.security.comentario.Comentario;
import com.api.security.estomago.Estomago;
import com.api.security.imagen.Imagen;
import com.api.security.publicacion.Publicacion;
import com.api.security.respuesta.Respuesta;
import com.api.security.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    
    private String nombreCompleto;
    private String nombreUsuario;
    private int pesoCorporal;
    private double cantidadActividad;
    
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Usuario usuario;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Imagen imgAvatar;
    
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Estomago estomago;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Publicacion> publicaciones;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;
}
