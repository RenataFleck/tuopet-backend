package com.example.tuopet.dto;

import com.example.tuopet.entity.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class AgendamentoDto {

    private Long id;
    private LocalDate data;
    private LocalTime horario;
    private Status status;
    private LocalTime duracao;
    private PetDto pet;

}
