package com.example.corestock.controller;

import com.example.corestock.dto.NuevaOrdenDTO;
import com.example.corestock.service.OrdenDeCompraService;
import com.example.corestock.service.ProductoService;
import com.example.corestock.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private OrdenDeCompraService comprasService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarOrdenes(Model model) {
        model.addAttribute("ordenes", comprasService.listarOrdenesActivas());
        return "compras";
    }

    @GetMapping("/nueva")
    public String nuevaOrden(Model model) {
        model.addAttribute("ordenDTO", new NuevaOrdenDTO());
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        model.addAttribute("productos", productoService.listarProductoActivo());
        return "nueva_orden";
    }

    @PostMapping("/nueva")
    public String guardarOrden(@ModelAttribute("ordenDTO") NuevaOrdenDTO dto, Model model) {
        try {
            comprasService.crearOrden(dto);
            return "redirect:/compras";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("proveedores", proveedorService.listarProveedores());
            model.addAttribute("productos", productoService.listarProductoActivo());
            return "nueva_orden";
        }
    }

    @GetMapping("/{id}")
    public String detalleOrden(@PathVariable String id, Model model) {
        try {
            model.addAttribute("orden", comprasService.buscarOrden(id));
            return "detalle_orden";
        } catch (Exception e) {
            return "redirect:/compras";
        }
    }
}
