package com.example.tuopet.service;

import com.example.tuopet.dto.AgendamentoDetalhes;
import com.example.tuopet.dto.AgendamentoDto;
import com.example.tuopet.dto.AgendamentoInsertDto;
import com.example.tuopet.entity.Agendamento;
import com.example.tuopet.entity.Servico;
import com.example.tuopet.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final TipoServicoService tipoServicoService;
    private final ServicoService servicoService;
    private final PetService petService;

    public List<LocalTime> gerarHorariosDisponiveis(List<Long> servicos, LocalDate data) {
        var duracao = tipoServicoService.buscarDuracaoServicos(servicos);
        var agendamentosDoDia = agendamentoRepository.findByData(data);

        var hoje = LocalDate.now();
        var limiteHoje = LocalTime.now().plusMinutes(30);

        return gerarHorariosPossiveis(duracao).stream()
                .filter(horario -> !data.equals(hoje) || !horario.isBefore(limiteHoje))
                .filter(horario -> !conflitaComExistente(horario, duracao, agendamentosDoDia))
                .toList();
    }

    private List<LocalTime> gerarHorariosPossiveis(Duration duracao) {
        var horarios = new ArrayList<LocalTime>();

        horarios.addAll(gerarHorariosIntervalo(LocalTime.of(9, 0), LocalTime.of(12, 0), duracao));
        horarios.addAll(gerarHorariosIntervalo(LocalTime.of(13, 0), LocalTime.of(19, 0), duracao));

        return horarios;
    }

    private List<LocalTime> gerarHorariosIntervalo(LocalTime inicio, LocalTime fim, Duration duracao) {
        var horarios = new ArrayList<LocalTime>();

        for (LocalTime horario = inicio; !horario.plus(duracao).isAfter(fim); horario = horario.plusMinutes(30)) {
            horarios.add(horario);
        }

        return horarios;
    }

    private boolean conflitaComExistente(LocalTime inicioProposto, Duration duracao, List<Agendamento> agendamentos) {
        var fimProposto = inicioProposto.plus(duracao);

        return agendamentos.stream().anyMatch(agendamento -> {
            var agendamentoHorario = agendamento.getHorario();

            var fimExistente = agendamentoHorario
                    .plus(Duration.between(LocalTime.MIDNIGHT, agendamento.getDuracao()));

            return !(fimProposto.isBefore(agendamentoHorario) || inicioProposto.isAfter(fimExistente));
        });
    }

    public List<AgendamentoDetalhes> buscarAgendamentosUsuario(Long usuarioId) {
        return petService.buscaPetsPorUsuario(usuarioId)
                .stream()
                .flatMap(pet -> agendamentoRepository.findByPetId(pet.getId())
                        .stream()
                        .map(agendamento -> {
                            var servicos = servicoService.buscaServicosPorAgendamento(agendamento.getId());
                            return agendamento.toAgendamentoDetalhes(servicos);
                        })
                )
                .toList();
    }

    public AgendamentoDto criaNovoAgendamento(AgendamentoInsertDto agendamentoInsertDto) {
        var pet = petService.buscaPet(agendamentoInsertDto.getPetId());
        var duracao = tipoServicoService.buscarDuracaoServicos(agendamentoInsertDto.getServicos());

        var agendamento = agendamentoRepository.save(agendamentoInsertDto.toAgendamento(pet, duracao));

        agendamentoInsertDto.getServicos()
                .forEach(idTipoServico -> {
                    var tipoServico = tipoServicoService.buscaTipoServico(idTipoServico);
                    servicoService.salvaServico(Servico.from(agendamento, tipoServico));
                });

        return agendamento.toAgendamentoDto();
    }

    public Agendamento buscaAgendamento(Long agendamentoId) {
        return agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));
    }

    public AgendamentoDto getAgendamento(Long agendamentoId) {
        return buscaAgendamento(agendamentoId)
                .toAgendamentoDto();
    }

    public AgendamentoDto getAgendamentosUsuario(Long usuarioId) {
        return buscaAgendamento(usuarioId)
                .toAgendamentoDto();
    }

    public AgendamentoDto alteraAgendamento(Long agendamentoId, AgendamentoInsertDto agendamentoInsertDto) {
        var agendamento = buscaAgendamento(agendamentoId);

        var novaDuracao = servicoService.validaServicosExistentes(agendamento, agendamentoInsertDto.getServicos());

        agendamento.atualizaCampos(agendamentoInsertDto, novaDuracao);

        return agendamentoRepository.save(agendamento)
                .toAgendamentoDto();
    }

    public void deleteAgendamento(Long agendamentoId) {
        agendamentoRepository.deleteById(agendamentoId);
    }
}
