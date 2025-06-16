package com.example.tuopet.repository;

import com.example.tuopet.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByAgendamentoId(Long agendamentoId);

}
