package com.example.tuopet.dto;

import com.example.tuopet.entity.*;
import com.example.tuopet.service.validation.PetInsertValid;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@PetInsertValid
public class PetInsertDto extends PetDto{

    public PetInsertDto(Long id, Sexo sexo, String raca, Pelo pelo, Double peso, String nome, Especie especie, boolean castrado, Date dataNascimento, Porte porte, Long usuarioId) {
        super(id, sexo, raca, pelo, peso, nome, especie, castrado, dataNascimento, porte, usuarioId);
    }

}
