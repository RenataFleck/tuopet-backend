package com.example.tuopet.controller;

import com.example.tuopet.dto.AgendamentoDetalhes;
import com.example.tuopet.dto.AgendamentoDto;
import com.example.tuopet.dto.AgendamentoInsertDto;
import com.example.tuopet.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping("/buscar-horarios")
    public ResponseEntity<List<LocalTime>> buscaHorariosDisponiveis(@RequestParam List<Long> servicos, @RequestParam LocalDate data) {
        return ResponseEntity.ok(agendamentoService.gerarHorariosDisponiveis(servicos, data));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AgendamentoDetalhes>> buscarAgendamentosUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentosUsuario(usuarioId));
    }

    @PostMapping("/inserir")
    public ResponseEntity<AgendamentoDto> criaNovoAgendamento(@RequestBody AgendamentoInsertDto agendamentoInsertDto) {
        return ResponseEntity.ok(agendamentoService.criaNovoAgendamento(agendamentoInsertDto));
    }

    @GetMapping("/{agendamentoId}")
    public ResponseEntity<AgendamentoDto> buscaAgendamento(@PathVariable Long agendamentoId) {
        return ResponseEntity.ok(agendamentoService.getAgendamento(agendamentoId));
    }

    @PutMapping("/{agendamentoId}")
    public ResponseEntity<AgendamentoDto> alteraAgendamento(@PathVariable Long agendamentoId, @RequestBody AgendamentoInsertDto agendamentoInsertDto) {
        return ResponseEntity.ok(agendamentoService.alteraAgendamento(agendamentoId, agendamentoInsertDto));
    }

    @DeleteMapping("/{agendamentoId}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long agendamentoId) {
        agendamentoService.deleteAgendamento(agendamentoId);
        return ResponseEntity.ok().build();
    }

}
