package com.example.colegio.entities;

import com.example.colegio.enums.Rol;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String correo;
    private String clave;
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
