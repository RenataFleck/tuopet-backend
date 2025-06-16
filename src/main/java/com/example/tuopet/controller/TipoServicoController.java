package com.example.tuopet.controller;

import com.example.tuopet.dto.TipoServicoDto;
import com.example.tuopet.service.TipoServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<TipoServicoDto>> buscaPetsPorUsuario(@PathVariable Long petId) {
        return ResponseEntity.ok(tipoServicoService.buscaServicosPeloPorte(petId));
    }

}
