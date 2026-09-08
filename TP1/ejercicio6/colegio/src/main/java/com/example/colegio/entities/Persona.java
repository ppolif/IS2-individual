package com.example.colegio.entities;

import com.example.colegio.enums.Sexo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.Date;

/**
 * @Entity: Define la clase como entidad JPA[cite: 9].
 * @Audited: Activa la auditoría de Hibernate Envers para registrar cambios[cite: 7].
 */
@Data
@Entity
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
}
