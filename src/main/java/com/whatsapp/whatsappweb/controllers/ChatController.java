package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.MensajeService;
import com.whatsapp.whatsappweb.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatController {
    private final MensajeService mensajeService;
    private final UsuarioService usuarioService;
    private Usuario usuario;

    public ChatController(MensajeService mensajeService, UsuarioService usuarioService) {
        this.mensajeService = mensajeService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/chat")
    public String cargarChat(
            @RequestParam("username") String username,
            HttpSession session,
            Model model
    ) {
        this.usuario = (Usuario) session.getAttribute("usuario");
        Usuario contacto = usuarioService.findByUsuario(username);

        model.addAttribute("nombreUsuario", contacto.getNombre());
        model.addAttribute("estado", contacto.getEstado());

        List<Mensaje> mensajes =
                mensajeService.getMensajeByUsuarioEmisorAndUsuarioReceptor(usuario.getUsuario(), username);

        List<Mensaje> mensajesContacto =
                mensajeService.getMensajeByUsuarioEmisorAndUsuarioReceptor(username, usuario.getUsuario());

        mensajes.addAll(mensajesContacto);

        model.addAttribute("mensajes", mensajes.stream().sorted(Comparator.comparing(Mensaje::getFecha)
                .reversed()).collect(Collectors.toList()));

        return "chat";
    }
}
