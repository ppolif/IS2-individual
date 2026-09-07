package com.example.club.controllers;

import com.example.club.entities.Familia;
import com.example.club.services.FamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/familias")
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    @GetMapping
    public String listarFamilias(Model model) {
        model.addAttribute("familias", familiaService.listarFamilias());
        return "familias_lista";
    }

    @GetMapping("/{id}")
    public String detalleFamilia(@PathVariable Long id, Model model) {
        try {
            Familia familia = familiaService.buscarPorId(id);
            model.addAttribute("familia", familia);
            return "familia_detalle";
        } catch (Exception e) {
            return "redirect:/familias";
        }
    }
}
