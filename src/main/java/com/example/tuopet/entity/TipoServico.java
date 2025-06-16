package com.example.tuopet.entity;

import com.example.tuopet.dto.TipoServicoDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "tb_tipo_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    private LocalTime duracao;

    private BigDecimal valorPacote;

    private BigDecimal valorIndividual;

    public TipoServicoDto toTipoServicoDto() {
        return TipoServicoDto.builder()
                .id(id)
                .nome(nome)
                .valor(valorIndividual)
                .build();
    }

}
