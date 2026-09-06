package com.example.corestock.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "proveedores")
@Data
public class Proveedor {
    @Id
    private Long cuit; // Se usa Long para evitar desbordamiento numérico (UML indica int)

    private String razonSocial;
    private boolean eliminado = false; // Para baja lógica

    // @ManyToMany: Relación muchos a muchos con Producto.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "proveedor_producto",
            joinColumns = @JoinColumn(name = "proveedor_cuit"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    @OneToMany(mappedBy = "proveedor")
    private List<OrdenDeCompra> ordenes;
}
