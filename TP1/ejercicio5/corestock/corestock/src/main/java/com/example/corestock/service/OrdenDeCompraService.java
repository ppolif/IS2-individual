package com.example.corestock.service;

import com.example.corestock.dto.NuevaOrdenDTO;
import com.example.corestock.dto.OrdenDeCompraDTO;
import com.example.corestock.entity.*;
import com.example.corestock.enumeraciones.*;
import com.example.corestock.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdenDeCompraService {

    @Autowired
    private OrdenDeCompraRepository ordenRepo;
    @Autowired
    private ProveedorRepository proveedorRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public void crearOrden(NuevaOrdenDTO dto) throws Exception {
        validar(dto);

        Proveedor prov = proveedorRepo.findById(dto.getProveedorCuit())
                .orElseThrow(() -> new Exception("Proveedor no encontrado"));

        OrdenDeCompra orden = new OrdenDeCompra();
        orden.setId("OC-" + UUID.randomUUID().toString().substring(0,8));
        orden.setFecha(new Date());
        orden.setProveedor(prov);
        orden.setEstado(EstadoOrden.COMPLETADA);
        orden.setEliminado(false);

        List<Detalle> detalles = new ArrayList<>();
        double total = 0;

        for (int i = 0; i < dto.getProductosIds().size(); i++) {
            Producto prod = productoRepo.findById(dto.getProductosIds().get(i))
                    .orElseThrow(() -> new Exception("Producto no encontrado"));

            Detalle detalle = new Detalle();
            detalle.setId(UUID.randomUUID().toString());
            detalle.setProducto(prod);
            detalle.setOrden(orden);
            detalle.setCantidad(dto.getCantidades().get(i));

            // Asume que la vigencia 0 es el precio actual
            double precioActual = prod.getVigencias().isEmpty() ? 0 : prod.getVigencias().get(0).getPrecio();
            detalle.setSubtotal(precioActual * detalle.getCantidad());
            detalle.setEliminado(false);

            detalles.add(detalle);
            total += detalle.getSubtotal();
        }

        orden.setDetalles(detalles);
        orden.setTotalPagado(total);
        // Guardamos primero la orden (cascada guarda también los Detalle).
        // Recién ahí los Detalle quedan persistidos y podemos referenciarlos desde Stock.
        ordenRepo.save(orden);

        // Movimiento de Stock Automático (Entrada): se acumula sobre el último stock registrado
        // de cada producto, en vez de pisarlo con la cantidad de esta orden.
        Map<String, Integer> stockAcumulado = new HashMap<>();
        for (Detalle detalle : detalles) {
            String prodId = detalle.getProducto().getId();
            int stockAnterior = stockAcumulado.containsKey(prodId)
                    ? stockAcumulado.get(prodId)
                    : obtenerUltimoStock(prodId);
            int nuevoStock = stockAnterior + detalle.getCantidad();
            stockAcumulado.put(prodId, nuevoStock);

            Stock stock = new Stock();
            stock.setDetalle(detalle);
            stock.setTipo(TipoMovimiento.ENTRADA);
            stock.setFechaMovimiento(new Date());
            stock.setCantActual(nuevoStock);
            stockRepository.save(stock);
        }
    }

    /**
     * Busca el último movimiento de stock registrado para un producto y devuelve
     * su cantidad actual (0 si el producto todavía no tuvo ningún movimiento).
     */
    private int obtenerUltimoStock(String productoId) {
        return stockRepository.findAll().stream()
                .filter(s -> s.getDetalle().getProducto().getId().equals(productoId))
                .reduce((first, second) -> second)
                .map(Stock::getCantActual)
                .orElse(0);
    }

    public void validar(NuevaOrdenDTO dto) throws Exception {
        if(dto.getProductosIds() == null || dto.getProductosIds().isEmpty()) {
            throw new Exception("La orden debe tener productos.");
        }
    }

    @Transactional
    public void eliminarOrden(String id) throws Exception {
        OrdenDeCompra orden = ordenRepo.findById(id).orElseThrow();
        orden.setEliminado(true);
        ordenRepo.save(orden);
    }

    @Transactional(readOnly = true)
    public List<OrdenDeCompraDTO> listarOrdenesActivas() {
        return ordenRepo.findAll().stream()
                .filter(o -> !o.isEliminado())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrdenDeCompraDTO buscarOrden(String id) throws Exception {
        return toDTO(ordenRepo.findById(id).orElseThrow(() -> new Exception("Orden no encontrada")));
    }

    private OrdenDeCompraDTO toDTO(OrdenDeCompra o) {
        OrdenDeCompraDTO dto = new OrdenDeCompraDTO();
        dto.setId(o.getId());
        dto.setProveedorNombre(o.getProveedor().getRazonSocial());
        dto.setFechaEmision(o.getFecha());
        dto.setTotal(o.getTotalPagado());
        dto.setEstado(o.getEstado().name());
        return dto;
    }
}
