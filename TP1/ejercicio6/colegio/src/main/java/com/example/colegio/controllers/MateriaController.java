package com.example.colegio.controllers;

import com.example.colegio.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/materias")
    public String listarMaterias(Model model) {
        model.addAttribute("materias", materiaService.listarTodas());
        return "materias";
    }

    @GetMapping("/materias/detalle/{id}")
    public String detalleMateria(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("materia", materiaService.buscarPorId(id));
            return "detalle_materia";
        } catch (Exception e) {
            return "redirect:/materias";
        }
    }
}