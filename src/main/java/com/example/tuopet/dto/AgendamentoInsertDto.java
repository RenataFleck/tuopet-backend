package com.example.tuopet.dto;

import com.example.tuopet.entity.Agendamento;
import com.example.tuopet.entity.Pet;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class AgendamentoInsertDto {

    private LocalDate data;
    private LocalTime horario;
    private Long petId;
    private List<Long> servicos;

    public Agendamento toAgendamento(Pet pet, Duration duracao) {
        return Agendamento.builder()
                .data(data)
                .horario(horario)
                .duracao(LocalTime.MIDNIGHT.plus(duracao))
                .pet(pet)
                .build();
    }

}
