package com.example.colegio.controllers;

import com.example.colegio.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alumno")
@PreAuthorize("hasRole('ALUMNO')")
public class AlumnoController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/materias")
    public String listarMaterias(Model model) {
        model.addAttribute("materias", materiaService.listarTodas());
        return "materias";
    }

    @GetMapping("/materias/detalle/{id}")
    public String detalleMateria(@PathVariable Long id, Model model, Authentication auth) {
        try {
            model.addAttribute("materia", materiaService.buscarPorId(id));
            model.addAttribute("yaInscripto", materiaService.estaInscripto(id, auth.getName()));
            return "detalle_materia";
        } catch (Exception e) {
            return "redirect:/alumno/materias";
        }
    }

    @PostMapping("/materias/{id}/inscribirse")
    public String inscribirse(@PathVariable Long id, Authentication auth, RedirectAttributes attrs) {
        try {
            materiaService.inscribirAlumno(id, auth.getName());
            attrs.addFlashAttribute("exito", "Te inscribiste correctamente en la materia.");
        } catch (Exception e) {
            attrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alumno/materias/detalle/" + id;
    }
}