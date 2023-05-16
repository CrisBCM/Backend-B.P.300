package com.api.security.auth;

import com.api.security.config.JwtService;
import com.api.security.usuario.Role;
import com.api.security.usuario.Usuario;
import com.api.security.usuario.UsuarioRepository;
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
        
        usuarioRepo.save(user);
        
        var jwtToken = jwtService.generateToken(user);
        
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
        
        
        var jwtToken = jwtService.generateToken(user);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
