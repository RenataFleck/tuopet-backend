package com.example.tuopet.entity;

import com.example.tuopet.dto.ServicoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

    @ManyToOne
    @JoinColumn(name = "tipo_servico_id")
    private TipoServico tipoServico;

    public static Servico from(Agendamento agendamento, TipoServico tipoServico) {
        return Servico.builder()
                .nome(tipoServico.getNome())
                .agendamento(agendamento)
                .tipoServico(tipoServico)
                .build();
    }

    public ServicoDto toServicoDto() {
        return ServicoDto.builder()
                .id(tipoServico.getId())
                .nome(tipoServico.getNome())
                .valor(tipoServico.getValorIndividual())
                .build();
    }

}
