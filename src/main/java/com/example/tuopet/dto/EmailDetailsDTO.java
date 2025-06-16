package com.example.tuopet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailDetailsDTO {

    private String para;
    private String assunto;
    private String corpo;

}
