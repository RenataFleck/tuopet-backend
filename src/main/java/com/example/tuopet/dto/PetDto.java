package com.example.tuopet.dto;

import com.example.tuopet.entity.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PetDto {

    private Long id;

    @NotNull
    private Sexo sexo;

    @NotNull
    private String raca;

    @NotNull
    private Pelo pelo;

    @DecimalMin("0.01")
    private Double peso;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull
    private Especie especie;

    @NotNull
    private Boolean castrado;

    @Past(message = "Data de nascimento inválida")
    private Date dataNascimento;

    @NotNull
    private Porte porte;

    @NotNull
    private Long usuarioId;

    public Pet toPet(Usuario usuario) {
        return Pet.builder()
                .castrado(castrado)
                .dataNascimento(dataNascimento)
                .especie(especie)
                .nome(nome)
                .pelo(pelo)
                .peso(peso)
                .porte(porte)
                .raca(raca)
                .especie(especie)
                .sexo(sexo)
                .usuario(usuario)
                .build();
    }

}
