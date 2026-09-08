package com.example.colegio.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Data
@Entity
@Audited
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private int capacidad;

    @OneToMany(mappedBy = "aula")
    private List<Materia> materias;
}
