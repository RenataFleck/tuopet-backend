package com.example.tuopet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum RacaGato {

    SIAMES("Siamês"),
    PERSA("Persa"),
    MAINE_COON("Maine Coon"),
    ANGORA_TURCO("Angorá Turco"),
    SPHYNX("Sphynx"),
    RAGDOLL("Ragdoll"),
    BENGAL("Bengal"),
    ABISSINIO("Abissínio"),
    AZUL_RUSSO("Azul Russo"),
    SCOTTISH_FOLD("Scottish Fold"),
    BURMESE("Burmese"),
    BIRMANES("Birmanês"),
    SAVANNAH("Savannah"),
    MUNCHKIN("Munchkin"),
    HIMALAIO("Himalaio"),
    AMERICAN_SHORTHAIR("American Shorthair"),
    ORIENTAL("Oriental"),
    CORNISH_REX("Cornish Rex"),
    DEVON_REX("Devon Rex"),
    PETERBALD("Peterbald"),
    LAPERM("LaPerm"),
    SOMALI("Somali"),
    MANX("Manx"),
    BALINES("Balinês");

    private final String descricao;

    public static List<String> getDescricoesRacasGato() {
        return Arrays.stream(values())
                .map(RacaGato::getDescricao)
                .sorted()
                .toList();
    }

}
