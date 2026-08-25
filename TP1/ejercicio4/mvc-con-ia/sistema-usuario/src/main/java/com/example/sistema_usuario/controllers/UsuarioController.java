package com.example.sistema_usuario.controllers;


import com.example.sistema_usuario.model.domain.Usuario;
import com.example.sistema_usuario.model.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CAPA DE CONTROLADOR (Controller)
 * @Controller: Marca esta clase como un controlador MVC clásico de Spring.
 * Su objetivo es atrapar peticiones HTTP, usar el Service y retornar plantillas Thymeleaf.
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

// ==========================================
    // INGRESO Y SESIÓN
    // ==========================================

    @GetMapping({"/", "/login"})
    public String mostrarLogin(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    /**
     * Agregamos HttpSession como parámetro. Spring Boot inyecta la sesión automáticamente.
     */
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String clave,
                                Model model,
                                HttpSession session) {
        try {
            boolean autenticado = usuarioService.autenticar(correo, clave);
            if (autenticado) {
                // EXITO: Guardamos el correo del usuario en la sesión del servidor
                session.setAttribute("usuarioLogueado", correo);
                return "redirect:/home";
            }
        } catch (Exception e) {
            String mensajeError = e.getMessage();

            if (mensajeError.equals("no_registrado")) {
                model.addAttribute("mensaje", "Usuario no encontrado. Por favor, regístrese.");
                model.addAttribute("mostrarRegistro", true);
                return "login";
            } else if (mensajeError.equals("cuenta_bloqueada")) {
                return "redirect:/login?error=Cuenta bloqueada por múltiples intentos fallidos.";
            } else if (mensajeError.equals("clave_incorrecta")) {
                return "redirect:/login?error=Contraseña incorrecta.";
            }
        }
        return "redirect:/login?error=Error desconocido";
    }

    /**
     * Cerrar sesión. Destruye los datos guardados.
     */
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        // Invalida (borra) la sesión actual
        session.invalidate();
        return "redirect:/login?error=Sesion cerrada exitosamente.";
    }

    // ==========================================
    // RUTAS PROTEGIDAS
    // ==========================================

    /**
     * Ahora la ruta /home está protegida. Solo se puede entrar si hay sesión.
     */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        // Recuperamos el dato que guardamos durante el login
        String correoUsuario = (String) session.getAttribute("usuarioLogueado");

        // Validamos si la sesión existe
        if (correoUsuario == null) {
            // Si es null, significa que no se logueó. Lo pateamos al login.
            return "redirect:/login?error=Acceso denegado. Debe iniciar sesion.";
        }

        // Si existe, le pasamos el correo a la vista para mostrar un saludo
        model.addAttribute("correo", correoUsuario);
        return "home";
    }

    // ==========================================
    // REGISTRO (Se mantiene igual)
    // ==========================================

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario,
                                   @RequestParam String repeticionClave,
                                   Model model) {
        try {
            usuarioService.registrarUsuario(usuario, repeticionClave);
            return "redirect:/login?error=Registro exitoso. Inicie sesion.";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}
