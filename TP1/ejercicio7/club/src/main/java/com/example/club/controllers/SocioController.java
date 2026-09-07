package com.example.club.controllers;

import com.example.club.entities.Socio;
import com.example.club.services.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public String listarSocios(Model model, @RequestParam(required = false) String q) {
        if (q != null && !q.isEmpty()) {
            model.addAttribute("socios", socioService.buscarPorNombreODni(q));
        } else {
            model.addAttribute("socios", socioService.listarTodos());
        }
        model.addAttribute("query", q);
        return "socios_lista";
    }

    @GetMapping("/{id}")
    public String detalleSocio(@PathVariable Long id, Model model) {
        try {
            Socio socio = socioService.buscarPorId(id);
            model.addAttribute("socio", socio);
            return "socio_detalle";
        } catch (Exception e) {
            return "redirect:/socios";
        }
    }
}