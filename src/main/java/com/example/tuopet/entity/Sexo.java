package com.example.tuopet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Sexo {

    MACHO("Macho"),
    FEMEA("Fêmea");

    private final String descricao;

    public static List<String> getDescricoesSexo() {
        return Arrays.stream(values())
                .map(Sexo::getDescricao)
                .toList();
    }

}
