package com.example.colegio.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int valor;

    @ManyToOne
    private Alumno alumno;
}
