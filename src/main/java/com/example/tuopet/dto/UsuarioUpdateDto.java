package com.example.tuopet.dto;

import com.example.tuopet.service.validation.UserUpdateValid;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@UserUpdateValid
public class UsuarioUpdateDto extends UsuarioDto{

    public UsuarioUpdateDto(Long id, String nomeCompleto, String email, String role, String cpf, String celular, String genero, LocalDate dataNascimento) {
        super(id, nomeCompleto, email, role, cpf, celular, genero, dataNascimento);
    }

}
