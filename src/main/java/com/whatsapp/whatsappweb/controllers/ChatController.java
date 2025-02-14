package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.services.MensajeService;
import com.whatsapp.whatsappweb.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatController {
    private final MensajeService mensajeService;
    private final UsuarioService usuarioService;
    private final SimpMessagingTemplate messagingTemplate;
    private Usuario usuario;
    private Usuario contacto;

    public ChatController(MensajeService mensajeService, UsuarioService usuarioService,
                          SimpMessagingTemplate messagingTemplate) {
        this.mensajeService = mensajeService;
        this.usuarioService = usuarioService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/chat")
    public String cargarChat(
            @RequestParam("username") String username,
            HttpSession session,
            Model model
    ) {
        usuario = (Usuario) session.getAttribute("usuario");
        contacto = usuarioService.findByUsuario(username);

        model.addAttribute("nombreUsuario", contacto.getNombre());
        model.addAttribute("usuario", contacto.getUsuario());

        List<Mensaje> mensajes =
                mensajeService.getMensajeByUsuarioEmisorAndUsuarioReceptor(usuario.getUsuario(), username);

        List<Mensaje> mensajesContacto =
                mensajeService.getMensajeByUsuarioEmisorAndUsuarioReceptor(username, usuario.getUsuario());

        mensajes.addAll(mensajesContacto);

        model.addAttribute("mensajes", mensajes.stream().sorted(Comparator.comparing(Mensaje::getFecha))
                .collect(Collectors.toList()));

        return "chat";
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMensaje(@Payload Mensaje mensaje) {
        if (usuario == null) {
            System.out.println("Usuario no encontrado en la sesión.");
            return;
        }

        mensaje.setUsuarioEmisor(usuario.getUsuario());
        mensaje.setUsuarioReceptor(contacto.getUsuario());
        mensaje.setFecha(LocalDateTime.now());

        mensajeService.save(mensaje);

        messagingTemplate.convertAndSendToUser(
                mensaje.getUsuarioReceptor(),
                "/queue/messages",
                mensaje
        );
    }
}
