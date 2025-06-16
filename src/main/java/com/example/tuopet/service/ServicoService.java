package com.example.tuopet.service;

import com.example.tuopet.entity.Agendamento;
import com.example.tuopet.entity.Servico;
import com.example.tuopet.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final TipoServicoService tipoServicoService;
    private final ServicoRepository servicoRepository;

    public void salvaServico(Servico servico) {
        servicoRepository.save(servico);
    }

    public List<Servico> buscaServicosPorAgendamento(Long agendamentoId) {
        return servicoRepository.findByAgendamentoId(agendamentoId);
    }

    public Duration validaServicosExistentes(Agendamento agendamento, List<Long> novosIds) {
        var servicosAtuais = buscaServicosPorAgendamento(agendamento.getId());
        var idsAtuais = servicosAtuais.stream()
                .map(Servico::getId)
                .collect(Collectors.toSet());

        var novosIdsSet = new HashSet<>(novosIds);

        novosIdsSet.stream()
                .filter(id -> !idsAtuais.contains(id))
                .map(tipoServicoService::buscaTipoServico)
                .map(tipo -> Servico.from(agendamento, tipo))
                .forEach(this::salvaServico);

        servicosAtuais.stream()
                .filter(servico -> !novosIdsSet.contains(servico.getId()))
                .forEach(servico -> servicoRepository.deleteById(servico.getId()));

        return tipoServicoService.buscarDuracaoServicos(novosIds);
    }


}
