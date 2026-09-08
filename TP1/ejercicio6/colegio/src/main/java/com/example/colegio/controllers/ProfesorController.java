package com.example.colegio.controllers;


import com.example.colegio.dto.CambioClaveDTO;
import com.example.colegio.services.AlumnoService;
import com.example.colegio.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profesor")
@PreAuthorize("hasRole('PROFESOR')") // Seguridad por rol
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AlumnoService alumnoService;


    @GetMapping("/alumnos/{materiaId}")
    public String listarAlumnosPorMateria(@PathVariable Long materiaId, Model model) {
        model.addAttribute("alumnos", alumnoService.listarPorMateria(materiaId));
        return "alumnos_lista";
    }

    @GetMapping("/cambiar-clave")
    public String mostrarCambioClave(Model model) {
        model.addAttribute("cambioClaveDTO", new CambioClaveDTO());
        return "cambio_clave";
    }

    @PostMapping("/cambiar-clave")
    public String procesarCambioClave(@ModelAttribute CambioClaveDTO dto, Authentication auth, Model model) {
        try {
            profesorService.cambiarClave(auth.getName(), dto);
            model.addAttribute("exito", "Contraseña actualizada exitosamente.");
            return "redirect:/materias";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "cambio_clave";
        }
    }
}