package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    private final UsuarioService usuarioService;
    private Usuario usuario;

    public ProfileController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/profile")
    public String profile(
            HttpSession session,
            Model model
    ) {
        usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        return "profile";
    }

    @PutMapping("/profile/update")
    public String updateProfile(@RequestParam("nombre") String nombre,
                                @RequestParam("apellidos") String apellidos,
                                @RequestParam("estado") String estado,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Model model,
                                HttpSession session){
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEstado(estado);
        usuario.setUsuario(username);
        usuario.setPassword(password);

        usuarioService.save(usuario);

        session.setAttribute("usuario", usuario);

        return "redirect:/profile";
    }
}
