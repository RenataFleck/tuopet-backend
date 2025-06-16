package com.example.tuopet.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class TipoServicoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;

}
