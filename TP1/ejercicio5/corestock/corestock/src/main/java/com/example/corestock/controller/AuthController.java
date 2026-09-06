package com.example.corestock.controller;

import com.example.corestock.dto.RegistroDTO;
import com.example.corestock.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuarioDTO", new RegistroDTO());
        return "registro";
    }

    /**
     * @PostMapping atrapa el envío del formulario.
     * @ModelAttribute captura el DTO enviado desde Thymeleaf para pasarlo limpio al Servicio[cite: 8].
     */
    @PostMapping("/registro")
    public String registrar(@ModelAttribute("usuarioDTO") RegistroDTO dto, Model model) {
        try {
            usuarioService.registrarUsuario(dto);
            return "redirect:/login?exito";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}
