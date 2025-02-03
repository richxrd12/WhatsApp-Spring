package com.whatsapp.whatsappweb.repositories;

import com.whatsapp.whatsappweb.entities.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MensajeRepository extends MongoRepository<Mensaje, String> {
    List<Mensaje> findByUsuarioEmisorAndUsuarioReceptor(String usuarioEmisor, String usuarioReceptor);
}
