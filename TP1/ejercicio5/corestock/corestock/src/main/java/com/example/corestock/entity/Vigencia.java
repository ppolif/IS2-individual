package com.example.corestock.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "vigencias")
@Data
public class Vigencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto prod;

    private double precio;

    @Temporal(TemporalType.DATE)
    private Date desde;

    @Temporal(TemporalType.DATE)
    private Date hasta;
}