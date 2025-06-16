package com.example.tuopet.controller;

import com.example.tuopet.dto.*;
import com.example.tuopet.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/inserir")
    public ResponseEntity<LoginResponseDTO> insertUsuario(@RequestBody @Valid UsuarioInsertDto usuarioInsertDto) {
        var usuarioDto = usuarioService.insertUsuario(usuarioInsertDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuarioDto.id()).toUri();

        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @PostMapping("/alterar-senha")
    public ResponseEntity<LoginResponseDTO> alterarSenha(@RequestBody AlterarSenhaDto alterarSenhaDto) {
        return ResponseEntity.ok(usuarioService.alterarSenha(alterarSenhaDto));
    }

    @PostMapping("/alterar-senha/solicitar")
    public ResponseEntity<Void> solicitaAlteraracaoSenha(@RequestBody EmailDto emailDto) {
        usuarioService.alterarSenha(emailDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/alterar-senha/validar")
    public ResponseEntity<Boolean> getValidacaoCodigo(@RequestBody CodigoDto codigoDto) {
        return ResponseEntity.ok(usuarioService.getValidacaoCodigo(codigoDto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDto> buscaUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.getUsuario(usuarioId));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDto> alteraUsuario(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioUpdateDto usuarioUpdateDto) {
        return ResponseEntity.ok(usuarioService.atualizaUsuario(usuarioId, usuarioUpdateDto));
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.ok().build();
    }

}
