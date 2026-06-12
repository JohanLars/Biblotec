package com.biblioteca;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {

        Usuario usuario = buscarPorId(id);

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setDocumento(usuarioActualizado.getDocumento());
        usuario.setCorreo(usuarioActualizado.getCorreo());

        return usuarioRepository.save(usuario);
    }

    public void desactivar(Long id) {

        Usuario usuario = buscarPorId(id);

        usuario.setActivo(false);

        usuarioRepository.save(usuario);
    }
}