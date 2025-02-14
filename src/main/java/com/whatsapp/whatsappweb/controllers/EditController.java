package com.whatsapp.whatsappweb.controllers;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.services.MensajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Tag(name = "Editar mensajes", description = "Controller que nos gestionala vista y el editado de mensajes")
public class EditController {
    private final MensajeService mensajeService;

    public EditController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }


    @GetMapping("/editar/{id}")
    @Operation(summary = "Muestra la vista para editar mensajes",
            description = "Nos muestra la vista para ver el mensaje, lo busca y lo añade al modelo (para mostrarlo en "
                    + "en la vista")
    public String editar(@PathVariable String id, Model model) {

        ObjectId idMensaje = new ObjectId(id);
        Mensaje mensaje = mensajeService.findById(idMensaje);

        model.addAttribute("mensaje", mensaje);

        return "editarMensaje";
    }

    @PatchMapping("/editar/{id}")
    @Operation(summary = "Edita el mensaje",
            description = "Edita el mensaje con el parámetro texto que coge del html")
    public String editarMensaje(@PathVariable String id,
                                @RequestParam("texto") String texto,
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
