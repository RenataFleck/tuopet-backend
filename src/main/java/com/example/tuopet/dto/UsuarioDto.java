package com.example.tuopet.dto;

import com.example.tuopet.entity.Role;
import com.example.tuopet.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class UsuarioDto {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nomeCompleto;

    @Email(message = "Email inválido")
    private String email;

    private String role;

    @CPF(message = "CPF inválido")
    private String cpf;

    @Pattern(regexp = "^(?:\\d{2})?\\d{9}$", message = "Número de telefone inválido")
    private String celular;

    @NotBlank(message = "Gênero é obrigatório")
    private String genero;

    @Past(message = "Data de nascimento inválida")
    private LocalDate dataNascimento;

    public Usuario toUsuario(Role role) {
        return Usuario.builder()
                .celular(celular)
                .nomeCompleto(nomeCompleto)
                .email(email)
                .cpf(cpf)
                .genero(genero)
                .dataNascimento(dataNascimento)
                .role(role)
                .build();
    }

}
