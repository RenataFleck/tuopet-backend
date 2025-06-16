package com.example.tuopet.entity;

import com.example.tuopet.dto.AgendamentoDetalhes;
import com.example.tuopet.dto.AgendamentoDto;
import com.example.tuopet.dto.AgendamentoInsertDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private LocalTime horario;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDENTE;

    private LocalTime duracao;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public AgendamentoDetalhes toAgendamentoDetalhes(List<Servico> servicos) {
        return AgendamentoDetalhes.builder()
                .id(id)
                .nomePet(pet.getNome())
                .data(data)
                .horario(horario)
                .servicos(servicos.stream().map(Servico::toServicoDto).toList())
                .build();
    }

    public AgendamentoDto toAgendamentoDto() {
        return AgendamentoDto.builder()
                .id(id)
                .data(data)
                .duracao(duracao)
                .horario(horario)
                .pet(pet.toPetDto())
                .duracao(duracao)
                .status(status)
                .build();
    }

    public void atualizaCampos(AgendamentoInsertDto agendamentoInsertDto, Duration novaDuracao) {
        setData(agendamentoInsertDto.getData());
        setHorario(agendamentoInsertDto.getHorario());
        setDuracao(LocalTime.MIDNIGHT.plus(novaDuracao));
    }

}
