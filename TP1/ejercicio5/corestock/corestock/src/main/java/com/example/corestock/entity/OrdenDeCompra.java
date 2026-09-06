package com.example.corestock.entity;

import com.example.corestock.entity.Detalle;
import com.example.corestock.entity.Proveedor;
import com.example.corestock.enumeraciones.EstadoOrden;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Capa de Persistencia: Representa la tabla de Órdenes en la BD.
 * NUNCA debe exponerse directamente a la vista para evitar fugas de datos y lazy loading exceptions.
 */
@Entity // Indica que es una entidad JPA
@Table(name = "ordenes_compra") // Mapea a la tabla
@Data // Lombok: genera getters, setters, toString, equals
public class OrdenDeCompra {

    @Id // Define la clave primaria
    private String id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private double totalPagado;
    private boolean eliminado;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum en la BD, no el ordinal
    private EstadoOrden estado;

    @ManyToOne(fetch = FetchType.LAZY) // Relación N a 1 con Proveedor
    @JoinColumn(name = "proveedor_cuit")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<Detalle> detalles;
}

