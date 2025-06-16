package com.example.tuopet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Porte {

    GRANDE("Grande"),
    MEDIO("Médio"),
    PEQUENO("Pequeno");

    private final String descricao;

    public static List<String> getDescricoesPorte() {
        return Arrays.stream(values())
                .map(Porte::getDescricao)
                .toList();
    }

}
