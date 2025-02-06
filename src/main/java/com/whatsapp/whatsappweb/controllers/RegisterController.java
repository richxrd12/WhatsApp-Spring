package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    private final UsuarioService usuarioService;

    public RegisterController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("estado") String estado,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("repeat-password") String repeatPassword,
            Model model
    ) {
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEstado(estado);
        usuario.setUsuario(username);
        usuario.setPassword(password);

        if (usuarioService.findByUsuario(username) != null) {
            model.addAttribute("error", "El usuario ya existe");
            return "register";
        } else if (!usuario.getPassword().equals(repeatPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "register";
        } else{
            usuarioService.save(usuario);
            model.addAttribute("success", "Usuario registrado con exito");
            return "index";
        }
    }
}
