package com.example.tuopet.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_alterar_senha")
public class AlterarSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String codigo;

    public static AlterarSenha from(Usuario usuario, String codigo) {
        return AlterarSenha.builder()
                .codigo(codigo)
                .usuario(usuario)
                .build();
    }

}
