package com.example.colegio.entities;

import com.example.colegio.enums.Grado;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Data
@Entity
@Audited
public class Alumno extends Persona {
    @Enumerated(EnumType.STRING)
    private Grado grado;

    private int legajo;

    @ManyToMany(mappedBy = "alumnos")
    private List<Materia> materiasCursadas;
}
