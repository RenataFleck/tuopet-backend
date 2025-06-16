package com.example.tuopet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlterarSenhaDto {

    private String senha;
    private String email;

    public AuthenticationDTO toAuthenticationDTO() {
        return new AuthenticationDTO(email, senha);
    }

}
