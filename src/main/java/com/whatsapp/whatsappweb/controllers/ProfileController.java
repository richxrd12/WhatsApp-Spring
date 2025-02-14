package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Tag(name = "Profile", description = "Controller para gestionar los datos del perfil del usuario")
public class ProfileController {
    private final UsuarioService usuarioService;
    private Usuario usuario;

    public ProfileController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/profile")
    @Operation(summary = "Muestra el perfil",
            description = "Nos redirige a la vista del perfil del usuario")
    public String profile(
            HttpSession session,
            Model model
    ) {
        usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);

        return "profile";
    }

    @PutMapping("/profile/update")
    @Operation(summary = "Update del usuario",
            description = "Actualiza el usuario con un método PUT con los input del html")
    public String updateProfile(@RequestParam("nombre") String nombre,
                                @RequestParam("apellidos") String apellidos,
                                @RequestParam("estado") String estado,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("repeat-password") String repeatPassword,
                                Model model,
                                HttpSession session){

        usuario = (Usuario) session.getAttribute("usuario");

        if (!password.equals(repeatPassword)) {
            model.addAttribute("error", "Los campos de contraseña deben coincidir.");
            model.addAttribute("usuario", usuario);
            return "profile";
        }

        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEstado(estado);
        usuario.setUsuario(username);
        usuario.setPassword(password);

        usuarioService.save(usuario);

        session.setAttribute("usuario", usuario);

        return "redirect:/profile";
    }

    @DeleteMapping("/profile/delete")
    @Operation(summary = "Delete user",
            description = "Busca el usuario que vamos a borrar (por si acaso), lo borramos y nos redirige al login")
    public String deleleUser(){

        Usuario usuarioBorrar = usuarioService.findByUsuario(usuario.getUsuario());

        usuarioService.delete(usuarioBorrar);

        return "redirect:/";
    }
}
