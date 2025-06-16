package com.example.tuopet.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class AgendamentoDetalhes {

    private Long id;
    private String nomePet;
    private LocalDate data;
    private LocalTime horario;
    private List<ServicoDto> servicos;

}
