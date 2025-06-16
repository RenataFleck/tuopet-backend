package com.example.tuopet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Pelo {

    CURTO("Curto"),
    LONGO("Longo");

    private final String descricao;

    public static List<String> getDescricoesPelo() {
        return Arrays.stream(values())
                .map(Pelo::getDescricao)
                .toList();
    }

}
