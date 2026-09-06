package com.example.corestock.mapper;

import com.example.corestock.dto.ProductoDTO;
import com.example.corestock.entity.Producto;
import com.example.corestock.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {
    public ProductoDTO toDto(Producto p, Stock s) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setMarca(p.getMarca());
        if(s != null) {
            dto.setStockActual(s.getCantActual());
            dto.setUltimaReposicion(s.getFechaMovimiento());
        } else {
            dto.setStockActual(0);
        }
        return dto;
    }
}
