package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Tag(name = "Home", description = "Controller para mostrar la vista de home")
public class HomeController {
    private final UsuarioService usuarioService;

    private Usuario usuario;

    public HomeController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/home")
    @Operation(summary = "Muestra el home",
            description = "Nos muestra la vista Home")
    public String home(Model model, HttpSession session) {
        //Para ordenarlos por nombre
        List<Usuario> usuarios = usuarioService.findAll().stream().sorted(Comparator.comparing(Usuario::getNombre))
                .collect(Collectors.toList());
        this.usuario = (Usuario) session.getAttribute("usuario");

        model.addAttribute("usuario", this.usuario);
        model.addAttribute("usuarios", usuarios);

        return "home";
    }

}
