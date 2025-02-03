package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final UsuarioService usuarioService;
    private Usuario usuario;

    public HomeController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        List<Usuario> usuarios = usuarioService.findAll();
        this.usuario = (Usuario) session.getAttribute("usuario");

        model.addAttribute("usuario", this.usuario);
        model.addAttribute("usuarios", usuarios);

        return "home";
    }
}
