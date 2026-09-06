package com.example.corestock.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductoDTO {
    private String id; // SKU
    private String nombre;
    private String marca;
    private String categoria; // Para el mockup
    private int stockActual;
    private Date ultimaReposicion;
    private boolean bajoUmbral;
    private double precio; // Precio inicial (se usa para crear la primera Vigencia)
}
