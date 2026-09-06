package com.example.corestock.dto;

import lombok.Data;

import java.util.List;

@Data
public class NuevaOrdenDTO {
    private Long proveedorCuit;
    private List<String> productosIds;
    private List<Integer> cantidades; }
