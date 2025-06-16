package com.example.tuopet.controller;

import com.example.tuopet.dto.PetDto;
import com.example.tuopet.dto.PetInsertDto;
import com.example.tuopet.dto.PetResources;
import com.example.tuopet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/inserir")
    public ResponseEntity<PetDto> criaNovoPet(@RequestBody PetInsertDto petInsertDto) {
        return ResponseEntity.ok(petService.criaNovoPet(petInsertDto));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDto> getPet(@PathVariable Long petId) {
        return ResponseEntity.ok(petService.getPet(petId));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetDto> alterarPet(@PathVariable Long petId, @RequestBody PetDto petDto) {
        return ResponseEntity.ok(petService.atualizaPet(petId, petDto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PetDto>> buscaPetsPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(petService.buscaPetsPorUsuario(usuarioId));
    }

    @GetMapping("/resources")
    public ResponseEntity<PetResources> buscaResources() {
        return ResponseEntity.ok(petService.buscaResources());
    }

}
