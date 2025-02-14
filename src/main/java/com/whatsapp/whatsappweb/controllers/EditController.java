package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.services.MensajeService;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditController {
    private final MensajeService mensajeService;

    public EditController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {

        ObjectId idMensaje = new ObjectId(id);
        Mensaje mensaje = mensajeService.findById(idMensaje);

        model.addAttribute("mensaje", mensaje);

        return "editarMensaje";
    }

    @PatchMapping("/editar/{id}")
    public String editarMensaje(@PathVariable String id,
                                @RequestParam("texto") String texto,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        ObjectId idMensaje = new ObjectId(id);
        Mensaje mensaje = mensajeService.findById(idMensaje);

        mensaje.setTexto(texto);
        mensaje.setEdited(true);

        mensajeService.save(mensaje);

        redirectAttributes.addAttribute("username", mensaje.getUsuarioReceptor());
        return "redirect:/chat";
    }
}
