package com.api.security.auth;

import com.api.security.config.JwtService;
import com.api.security.estomago.Estomago;
import com.api.security.estomago.EstomagoService;
import com.api.security.imagen.Imagen;
import com.api.security.imagen.ImagenService;
import com.api.security.persona.Persona;
import com.api.security.persona.PersonaRepository;
import com.api.security.usuario.Role;
import com.api.security.usuario.Usuario;
import com.api.security.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UsuarioRepository usuarioRepo;
    private final PersonaRepository personaRepo;
    private final PasswordEncoder passEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final ImagenService imgService;
    
    public boolean existsByEmail(String email){
        return usuarioRepo.existsByEmail(email);
    }
    public boolean existsByNombreUsuario(String nombreUsuario){
        return personaRepo.existsByNombreUsuario(nombreUsuario);
    }
    
    public AuthenticationResponse register (RegisterRequest request, HttpServletRequest httpRequest){
        
        String url = imgService.obtenerUrlImagen(httpRequest, "avatar1.jpg");
        
        Imagen imagen = Imagen.builder()
                .path(url)
                .nombre("avatar1.jpg")
                .build();
        
        var persona = Persona.builder()
            .nombreCompleto(request.getNombreCompleto())
            .nombreUsuario(request.getNombreUsuario())
            .imgAvatar(imagen)
            .pesoCorporal(request.getPesoCorporal())
            .cantidadActividad(request.getCantidadActividad())
            .build();
        
        var user = Usuario.builder()
            .email(request.getEmail())
            .contraseña(passEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .persona(persona)
            .build();
             
        
        var estomago = Estomago.builder()
            .listaComidas(new ArrayList())
            .totalConsumido(0)
            .persona(persona)
            .build();
        
          
        persona.setEstomago(estomago);
        persona.setUsuario(user);
        
        personaRepo.save(persona);
        
        Map<String, Object> extraClaims = new HashMap<String, Object>();
        
        extraClaims.put("persona_id", persona.getId());
        
        var jwtToken = jwtService.generateToken(extraClaims,user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        
        
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Usuario user = usuarioRepo.findByEmail(request.getEmail())
                .orElseThrow();
  
        Persona persona = user.getPersona();
        
        Map<String, Object> extraClaims = new HashMap<String, Object>();
        
        extraClaims.put("persona_id", persona.getId());

        
        String jwtToken = jwtService.generateToken(extraClaims,user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
