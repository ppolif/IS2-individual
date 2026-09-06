package com.example.corestock.service;

import com.example.corestock.dto.ProductoDTO;
import com.example.corestock.entity.Producto;
import com.example.corestock.entity.Stock;
import com.example.corestock.repository.ProductoRepository;
import com.example.corestock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarInventario() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(p -> {
            ProductoDTO dto = new ProductoDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setMarca(p.getMarca());

            // Busca el stock histórico de este producto
            Stock stockReciente = stockRepository.findAll().stream()
                    .filter(s -> s.getDetalle().getProducto().getId().equals(p.getId()))
                    .reduce((first, second) -> second)
                    .orElse(null);

            if(stockReciente != null) {
                dto.setStockActual(stockReciente.getCantActual());
                dto.setUltimaReposicion(stockReciente.getFechaMovimiento());
            } else {
                dto.setStockActual(0);
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
