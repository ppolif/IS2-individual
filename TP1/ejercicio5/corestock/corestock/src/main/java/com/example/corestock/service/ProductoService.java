package com.example.corestock.service;

import com.example.corestock.dto.ProductoDTO;
import com.example.corestock.entity.Producto;
import com.example.corestock.entity.Vigencia;
import com.example.corestock.repository.ProductoRepository;
import com.example.corestock.repository.VigenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VigenciaRepository vigenciaRepository;

    @Transactional
    public void crearProducto(ProductoDTO dto) throws Exception {
        validar(dto);

        if (productoRepository.existsById(dto.getId())) {
            throw new Exception("Ya existe un producto con ese ID (SKU)");
        }

        Producto p = new Producto();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setMarca(dto.getMarca());
        p.setEliminado(false);
        p.setVigencias(new ArrayList<>());
        productoRepository.save(p);

        // Si se cargó un precio inicial, generamos la primera vigencia de precio.
        // Sin esto, las órdenes de compra calcularían el subtotal en $0.
        if (dto.getPrecio() > 0) {
            Vigencia v = new Vigencia();
            v.setProd(p);
            v.setPrecio(dto.getPrecio());
            v.setDesde(new Date());
            v.setHasta(null);
            vigenciaRepository.save(v);
        }
    }

    public void validar(ProductoDTO dto) throws Exception {
        if (dto.getId() == null || dto.getId().isEmpty()) throw new Exception("ID inválido");
        if (dto.getNombre() == null || dto.getNombre().isEmpty()) throw new Exception("Nombre inválido");
    }

    @Transactional
    public void modificarProducto(String id, ProductoDTO dto) throws Exception {
        validar(dto);
        Producto p = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
        p.setNombre(dto.getNombre());
        p.setMarca(dto.getMarca());
        productoRepository.save(p);
    }

    @Transactional
    public void eliminarProducto(String id) throws Exception {
        Producto p = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
        p.setEliminado(true); // Baja lógica
        productoRepository.save(p);
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarProductoActivo() {
        return productoRepository.findAll().stream()
                .filter(p -> !p.isEliminado())
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoDTO buscarProducto(String id) throws Exception {
        Producto p = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
        return toDTO(p);
    }

    private ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setMarca(p.getMarca());
        return dto;
    }
}
