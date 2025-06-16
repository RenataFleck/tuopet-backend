package com.example.tuopet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoleDto {

    private Long id;
    private String autoridade;

}
