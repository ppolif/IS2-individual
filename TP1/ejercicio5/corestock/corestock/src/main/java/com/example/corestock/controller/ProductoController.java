package com.example.corestock.controller;

import com.example.corestock.dto.ProductoDTO;
import com.example.corestock.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Recibe el alta de producto desde el modal de la vista de Inventario.
     * Usamos RedirectAttributes (flash) para poder mostrar el error de vuelta
     * en /inventario después del redirect (patrón Post/Redirect/Get).
     */
    @PostMapping("/nuevo")
    public String crear(@ModelAttribute("productoDTO") ProductoDTO dto, RedirectAttributes redirectAttributes) {
        try {
            productoService.crearProducto(dto);
            redirectAttributes.addFlashAttribute("exito", "Producto creado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/inventario";
    }
}
