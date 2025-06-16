package com.example.tuopet.service;

import com.example.tuopet.dto.PetDto;
import com.example.tuopet.dto.PetInsertDto;
import com.example.tuopet.dto.PetResources;
import com.example.tuopet.entity.Pet;
import com.example.tuopet.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.tuopet.entity.Especie.getDescricoesEspecies;
import static com.example.tuopet.entity.Pelo.getDescricoesPelo;
import static com.example.tuopet.entity.Porte.getDescricoesPorte;
import static com.example.tuopet.entity.RacaCachorro.getDescricoesRacasCachorro;
import static com.example.tuopet.entity.RacaGato.getDescricoesRacasGato;
import static com.example.tuopet.entity.Sexo.getDescricoesSexo;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UsuarioService usuarioService;

    public List<PetDto> buscaPetsPorUsuario(Long usuarioId) {
        return petRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(Pet::toPetDto)
                .toList();
    }

    public PetDto criaNovoPet(PetInsertDto petInsertDto) {
        var usuario = usuarioService.buscaUsuario(petInsertDto.getUsuarioId());
        var save = petRepository.save(petInsertDto.toPet(usuario));

        return save.toPetDto();
    }

    public PetDto atualizaPet(Long petId, PetDto petDto) {
        var pet = buscaPet(petId);
        pet.atualizaCampos(petDto);

        var save = petRepository.save(pet);

        return save.toPetDto();
    }

    public PetResources buscaResources() {
        return PetResources.builder()
                .especies(getDescricoesEspecies())
                .sexos(getDescricoesSexo())
                .racasCachorro(getDescricoesRacasCachorro())
                .racasGato(getDescricoesRacasGato())
                .pelos(getDescricoesPelo())
                .portes(getDescricoesPorte())
                .build();
    }

    public Pet buscaPet(Long idPet) {
        return petRepository.findById(idPet)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));
    }

    public PetDto getPet(Long idPet) {
        return buscaPet(idPet)
                .toPetDto();
    }

    public void deletePet(Long idPet) {
        petRepository.deleteById(idPet);
    }

}
