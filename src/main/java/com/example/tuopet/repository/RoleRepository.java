package com.example.tuopet.repository;

import com.example.tuopet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAutoridade(String autoridade);

}
