package com.example.club.controllers;


import com.example.club.services.AccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/accesos")
public class AccesoController {

    @Autowired
    private AccesoService accesoService;

    @GetMapping("/hoy")
    public String verAccesos(Model model,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                             @RequestParam(required = false) String query) {
        if (fecha == null) {
            fecha = LocalDate.now();
        }
        // Usamos Model.addAttribute para llevar los datos a la bandeja de Thymeleaf[cite: 12]
        model.addAttribute("accesos", accesoService.listarPorFechaYFiltro(fecha, query));
        model.addAttribute("fechaSeleccionada", fecha);
        model.addAttribute("query", query);
        return "accesos_lista";
    }
}
