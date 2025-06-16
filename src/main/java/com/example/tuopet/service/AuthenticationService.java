package com.example.tuopet.service;

import com.example.tuopet.config.TokenService;
import com.example.tuopet.dto.AuthenticationDTO;
import com.example.tuopet.dto.LoginResponseDTO;
import com.example.tuopet.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponseDTO login(AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.usuario(), authenticationDTO.senha());
        var auth = authenticationManager.authenticate(usernamePassword);

        return new LoginResponseDTO(tokenService.generateToken((Usuario) auth.getPrincipal()),
                ((Usuario) auth.getPrincipal()).getId(),
                ((Usuario) auth.getPrincipal()).getEmail(),
                ((Usuario) auth.getPrincipal()).getDataNascimento(),
                ((Usuario) auth.getPrincipal()).getCelular(),
                ((Usuario) auth.getPrincipal()).getNomeCompleto(),
                ((Usuario) auth.getPrincipal()).getCpf(),
                ((Usuario) auth.getPrincipal()).getGenero());
    }
}
