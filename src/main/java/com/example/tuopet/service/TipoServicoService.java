package com.example.tuopet.service;

import com.example.tuopet.dto.TipoServicoDto;
import com.example.tuopet.entity.TipoServico;
import com.example.tuopet.repository.TipoServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;
    private final PetService petService;

    public TipoServico buscaTipoServico(Long idTipoServico) {
        return tipoServicoRepository.findById(idTipoServico)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de serviço não encontrado"));
    }

    public List<TipoServicoDto> buscaServicosPeloPorte(Long petId) {
        var porte = petService.buscaPet(petId).getPorte();

        return tipoServicoRepository.findByPorte(porte)
                .stream()
                .map(TipoServico::toTipoServicoDto)
                .toList();
    }

    public Duration buscarDuracaoServicos(List<Long> servicos) {
        return servicos.stream()
                .map(tipoServicoRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(TipoServico::getDuracao)
                .filter(Objects::nonNull)
                .map(duracao -> Duration.between(LocalTime.MIDNIGHT, duracao))
                .reduce(Duration.ZERO, Duration::plus);
    }

}
