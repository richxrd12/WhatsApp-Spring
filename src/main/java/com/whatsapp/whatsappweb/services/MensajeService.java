package com.whatsapp.whatsappweb.services;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.repositories.MensajeRepository;

import java.util.List;

public class MensajeService {
    private final MensajeRepository mensajeRepository;

    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public List<Mensaje> getMensajeByUsuarioEmisorAndUsuarioReceptor(String usuarioEmisor, String usuarioReceptor) {
        return mensajeRepository.findByUsuarioEmisorAndUsuarioReceptor(
                usuarioEmisor, usuarioReceptor
        );
    }
}
