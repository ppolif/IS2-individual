package com.example.corestock.entity;

import com.example.corestock.enumeraciones.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne: Cada movimiento de detalle genera un registro de stock
    @OneToOne
    @JoinColumn(name = "detalle_id")
    private Detalle detalle;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    private int cantActual;
    private int umbralReposicion;
    private int cantMax;
}
