package com.example.tuopet.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PetResources {

    private List<String> racasCachorro;
    private List<String> racasGato;
    private List<String> sexos;
    private List<String> especies;
    private List<String> pelos;
    private List<String> portes;

}
