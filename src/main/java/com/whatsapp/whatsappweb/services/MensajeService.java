package com.whatsapp.whatsappweb.services;

import com.whatsapp.whatsappweb.entities.Mensaje;
import com.whatsapp.whatsappweb.repositories.MensajeRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Mensaje findById(ObjectId id) {
        return mensajeRepository.findById(id).orElse(null);
    }

    public void save(Mensaje mensaje) {
        mensajeRepository.save(mensaje);
    }
}
