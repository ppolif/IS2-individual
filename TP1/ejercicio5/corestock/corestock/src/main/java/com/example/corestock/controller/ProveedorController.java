package com.example.corestock.controller;

import com.example.corestock.dto.ProveedorDTO;
import com.example.corestock.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        model.addAttribute("proveedorDTO", new ProveedorDTO());
        return "proveedores";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("proveedorDTO") ProveedorDTO dto, RedirectAttributes redirectAttributes) {
        try {
            if (dto.getCuit() != null && proveedorService.existeProveedor(dto.getCuit())) {
                proveedorService.modificarProveedor(dto.getCuit(), dto);
            } else {
                proveedorService.crearProveedor(dto);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/proveedores";
    }

    @PostMapping("/eliminar/{cuit}")
    public String eliminar(@PathVariable Long cuit) {
        proveedorService.eliminarProveedor(cuit);
        return "redirect:/proveedores";
    }
}
