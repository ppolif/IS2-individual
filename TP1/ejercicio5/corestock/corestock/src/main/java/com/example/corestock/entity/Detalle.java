package com.example.corestock.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalles")
@Data
public class Detalle {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenDeCompra orden;

    private int cantidad;
    private double subtotal;
    private boolean eliminado = false;
}