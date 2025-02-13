package com.whatsapp.whatsappweb.repositories;

import com.whatsapp.whatsappweb.entities.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findAll();
    Usuario findUsuarioByUsuario(String usuario);
}
