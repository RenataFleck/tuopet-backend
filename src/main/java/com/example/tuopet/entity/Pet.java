package com.example.tuopet.entity;

import com.example.tuopet.dto.PetDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_pet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String raca;

    @Enumerated(EnumType.STRING)
    private Pelo pelo;

    private Double peso;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Especie especie;

    private boolean castrado;

    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public PetDto toPetDto() {
        return PetDto.builder()
                .castrado(castrado)
                .dataNascimento(dataNascimento)
                .especie(especie)
                .nome(nome)
                .pelo(pelo)
                .peso(peso)
                .porte(porte)
                .raca(raca)
                .especie(especie)
                .sexo(sexo)
                .usuarioId(usuario.getId())
                .id(id)
                .build();
    }

    public void atualizaCampos(PetDto petDto) {
        setCastrado(petDto.getCastrado());
        setDataNascimento(petDto.getDataNascimento());
        setEspecie(petDto.getEspecie());
        setNome(petDto.getNome());
        setPelo(petDto.getPelo());
        setPeso(petDto.getPeso());
        setPorte(petDto.getPorte());
        setRaca(petDto.getRaca());
        setSexo(petDto.getSexo());
    }

}
