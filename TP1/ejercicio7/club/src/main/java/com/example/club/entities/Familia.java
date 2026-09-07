package com.example.club.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import java.util.List;

@Data
@Entity
@Audited
@Table(name = "familia")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apellidoFamilia;

    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL)
    private List<Socio> socios; // Titulares

    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL)
    private List<Familiar> familiares; // Adherentes
}
