package com.whatsapp.whatsappweb.repositories;

import com.whatsapp.whatsappweb.entities.Mensaje;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MensajeRepository extends MongoRepository<Mensaje, ObjectId> {
    List<Mensaje> findByUsuarioEmisorAndUsuarioReceptor(String usuarioEmisor, String usuarioReceptor);
}
