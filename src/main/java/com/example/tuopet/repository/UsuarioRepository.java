package com.example.tuopet.repository;

import com.example.tuopet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailOrCpf(String email, String cpf);
    Usuario findByEmail(String email);
}
