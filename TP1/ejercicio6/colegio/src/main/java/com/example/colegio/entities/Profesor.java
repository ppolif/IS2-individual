package com.example.colegio.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Data
@Entity
@Audited
public class Profesor extends Persona {
    private String titulo;

    @OneToMany(mappedBy = "profesor")
    private List<Materia> materiasImpartidas;
}