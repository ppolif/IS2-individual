package com.example.corestock.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    private String id;

    private String nombre;
    private String marca;
    private boolean eliminado = false;

    // @OneToMany: Un producto tiene un historial (muchas) vigencias de precio. Composición.
    @OneToMany(mappedBy = "prod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vigencia> vigencias;

    @ManyToMany(mappedBy = "productos")
    private List<Proveedor> proveedores;
}
