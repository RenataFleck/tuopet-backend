package com.example.tuopet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum RacaCachorro {

    LABRADOR_RETRIEVER("Labrador Retriever"),
    BULDOGUE_INGLES("Buldogue Inglês"),
    PASTOR_ALEMAO("Pastor Alemão"),
    GOLDEN_RETRIEVER("Golden Retriever"),
    BEAGLE("Beagle"),
    BULDOGUE_FRANCES("Buldogue Francês"),
    PUG("Pug"),
    SHIH_TZU("Shih Tzu"),
    YORKSHIRE_TERRIER("Yorkshire Terrier"),
    DACHSHUND("Dachshund"),
    PASTOR_AUSTRALIANO("Pastor Australiano"),
    BORDER_COLLIE("Border Collie"),
    BOXER("Boxer"),
    ROTTWEILER("Rottweiler"),
    SIBERIAN_HUSKY("Siberian Husky"),
    COCKER_SPANIEL("Cocker Spaniel"),
    MALTES("Maltês"),
    BICHON_FRISE("Bichon Frisé"),
    DALMATIANO("Dálmata"),
    CHIHUAHUA("Chihuahua"),
    POMERANIA("Pomerânia"),
    AKITA("Akita"),
    SAMOIEDA("Samoieda"),
    BASSET_HOUND("Basset Hound"),
    WHIPPET("Whippet"),
    SHIBA_INU("Shiba Inu"),
    GRANDE_DANES("Grande Danois"),
    SAINT_BERNARD("Saint Bernard"),
    MASTIFF("Mastiff"),
    BOSTON_TERRIER("Boston Terrier"),
    WEIMARANER("Weimaraner"),
    DOGO_ARGENTINO("Dogo Argentino"),
    STAFFORDSHIRE_TERRIER("Staffordshire Terrier"),
    JACK_RUSSELL_TERRIER("Jack Russell Terrier"),
    NOVA_SCOTIA_DUCK_TOLLING_RETRIEVER("Nova Scotia Duck Tolling Retriever");

    private final String descricao;

    public static List<String> getDescricoesRacasCachorro() {
        return Arrays.stream(values())
                .map(RacaCachorro::getDescricao)
                .sorted()
                .toList();
    }

}
