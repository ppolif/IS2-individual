package com.example.sesiones.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    //Base de Datos "Lógica"
    private Map<String, String> usuarios = new HashMap<>();

    public LoginController() {

        usuarios.put("Luisina", "1234");
        usuarios.put("TodoCode", "java");

    }

    //Login
    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }

    // Inicio de sesión
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombreUsuario,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        //validar que el usuario exista
        if (usuarios.containsKey(nombreUsuario)) {
            //validar la contraseña
            String contra = usuarios.get(nombreUsuario);
            if (contra.equals(password)) {
                session.setAttribute("usuarioLogueado", nombreUsuario);

                //aca usa redirect en vez de solo return porque ahora hay datos de sesion
                //que hay que mantener
                return "redirect:/bienvenida";
            }
        }

        //si no existe el usuario o si falla la contraseña
        model.addAttribute("error", "Usuario o contraseña erróneo");

        return "login";
    }

    //Página de Bienvenida
    @GetMapping("/bienvenida")
    public String mostrarBienvenida (HttpSession session) {

        //acá se hace un cast porque getAttribute devuelve Object
        String usuario = (String) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        return "bienvenida";

    }

    //Cerrar Sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {

        session.invalidate();
        return "redirect:/login";
    }


}
