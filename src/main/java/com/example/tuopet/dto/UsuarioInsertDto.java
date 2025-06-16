package com.example.tuopet.dto;

import com.example.tuopet.service.validation.UserInsertValid;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@UserInsertValid
public class UsuarioInsertDto extends UsuarioDto{

    private String senha;

    public UsuarioInsertDto(Long id, String nomeCompleto, String email, String role, String cpf, String celular, String genero, LocalDate dataNascimento) {
        super(id, nomeCompleto, email, role, cpf, celular, genero, dataNascimento);
    }

    public AuthenticationDTO toAuthenticationDTO() {
        return new AuthenticationDTO(getEmail(), senha);
    }

}
