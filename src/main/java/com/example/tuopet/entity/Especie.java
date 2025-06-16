package com.example.tuopet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Especie {

    GATO("Gato"),
    CACHORRO("Cachorro");

    private final String descricao;

    public static List<String> getDescricoesEspecies() {
        return Arrays.stream(values())
                .map(Especie::getDescricao)
                .toList();
    }

}
