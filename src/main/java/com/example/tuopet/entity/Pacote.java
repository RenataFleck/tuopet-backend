package com.example.tuopet.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "tb_pacote")
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
