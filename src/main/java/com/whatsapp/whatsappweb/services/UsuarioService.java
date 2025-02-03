package com.whatsapp.whatsappweb.services;

import com.whatsapp.whatsappweb.entities.Usuario;
import com.whatsapp.whatsappweb.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByUsuario(String username) {
        return usuarioRepository.findUsuarioByUsuario(username);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
