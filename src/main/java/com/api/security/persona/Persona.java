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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;

import lombok.*;

@Getter
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

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPesoCorporal(int pesoCorporal) {
        this.pesoCorporal = pesoCorporal;
    }

    public void setCantidadActividad(double cantidadActividad) {
        this.cantidadActividad = cantidadActividad;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setImgAvatar(Imagen imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public void setEstomago(Estomago estomago) {
        this.estomago = estomago;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
    public void addPublicacion(Publicacion publicacion){
        publicacion.setPersona(this);
        publicaciones.add(publicacion);
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
