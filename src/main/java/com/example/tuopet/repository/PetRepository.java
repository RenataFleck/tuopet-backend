package com.example.tuopet.repository;

import com.example.tuopet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByUsuarioId(Long usuarioId);
    Optional<Pet> findByUsuarioIdAndNome(Long usuarioId, String nome);

}
