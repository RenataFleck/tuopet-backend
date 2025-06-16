package com.example.tuopet.repository;

import com.example.tuopet.entity.AlterarSenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlterarSenhaRepository extends JpaRepository<AlterarSenha, Long> {

    AlterarSenha findByUsuarioId(Long usuarioId);

}
