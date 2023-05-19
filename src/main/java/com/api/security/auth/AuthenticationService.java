package com.api.security.auth;

import com.api.security.config.JwtService;
import com.api.security.persona.Persona;
import com.api.security.usuario.Role;
import com.api.security.usuario.Usuario;
import com.api.security.usuario.UsuarioRepository;
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
    private final PasswordEncoder passEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    
    public AuthenticationResponse register (RegisterRequest request){
        
        var user = Usuario.builder()
            .nombreUsuario(request.getUsername())
            .contraseña(passEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        
        var persona = Persona.builder()
            .nombre(request.getNombre())
            .apellido(request.getApellido())
            .pesoCorporal(request.getPesoCorporal())
            .cantidadActividad(request.getCantidadActividad())
            .usuario(user)
            .build();
        
        user.setPersona(persona);
        
        usuarioRepo.save(user);
        
        Map<String, Object> extraClaims = new HashMap<String, Object>();
        
        extraClaims.put("nombre", persona.getNombre());
        extraClaims.put("apellido", persona.getApellido());
        extraClaims.put("pesoCorporal", persona.getPesoCorporal());
        extraClaims.put("cantidadActividad", persona.getCantidadActividad());
        
        var jwtToken = jwtService.generateToken(extraClaims,user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = usuarioRepo.findByNombreUsuario(request.getUsername())
                .orElseThrow();
        
        var datosPersona = user.getPersona();
        
        Map<String, Object> extraClaims = new HashMap<String, Object>();
        
        extraClaims.put("nombre", datosPersona.getNombre());
        extraClaims.put("apellido", datosPersona.getApellido());
        extraClaims.put("pesoCorporal", datosPersona.getPesoCorporal());
        extraClaims.put("cantidadActividad", datosPersona.getCantidadActividad());
        
        var jwtToken = jwtService.generateToken(extraClaims,user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
