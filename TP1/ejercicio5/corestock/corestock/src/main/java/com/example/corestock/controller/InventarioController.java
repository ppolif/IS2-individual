package com.example.corestock.controller;

import com.example.corestock.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PreAuthorize("hasAnyRole('OPERADOR')")
    @GetMapping("/inventario")
    public String verInventario(Model model) {
        // La interfaz Model es el contenedor ("bandeja") que lleva los datos del BackEnd al HTML[cite: 7].
        model.addAttribute("productos", inventarioService.listarInventario());
        return "inventario";
    }
}
