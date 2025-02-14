package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.services.MensajeService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EditController {
    private final MensajeService mensajeService;

    public EditController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {

        ObjectId idMensaje = new ObjectId(id);
        return "editarMensaje";
    }
}
