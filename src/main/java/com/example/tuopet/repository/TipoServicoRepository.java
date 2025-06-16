package com.example.tuopet.repository;

import com.example.tuopet.entity.Porte;
import com.example.tuopet.entity.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {

    List<TipoServico> findByPorte(Porte porte);

}
