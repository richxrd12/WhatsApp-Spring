package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session
    ) {
        Usuario usuario = usuarioService.findByUsuario(username);

        if (usuario.getPassword().equals(password)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/home";
        }else{
            model.addAttribute("error", "Usuario o contraseña no son correctos.");
            return "index";
        }
    }

}
