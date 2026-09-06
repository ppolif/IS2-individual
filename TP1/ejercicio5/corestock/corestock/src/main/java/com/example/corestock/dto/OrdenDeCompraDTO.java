package com.example.corestock.dto;

import lombok.Data;
import java.util.Date;

/**
 * DTO para la vista de listado de compras.
 * Solo contiene los datos exactos que la tabla HTML necesita renderizar.
 */
@Data
public class OrdenDeCompraDTO {
    private String id;
    private String proveedorNombre;
    private Date fechaEmision;
    private double total;
    private String estado;
}
