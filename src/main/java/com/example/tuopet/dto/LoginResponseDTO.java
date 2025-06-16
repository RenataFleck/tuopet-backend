package com.example.tuopet.dto;

import java.time.LocalDate;

public record LoginResponseDTO(String token, Long id, String email, LocalDate dataNascimento, String celular, String nomeCompleto, String cpf, String genero) {
}
