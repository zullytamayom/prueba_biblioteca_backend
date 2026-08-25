package com.pruebabiblioteca.fullstack.service;


import com.pruebabiblioteca.fullstack.model.Usuario;
import com.pruebabiblioteca.fullstack.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> usuarioList(){
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> usuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario actualizarUsuario(Long id,Usuario datosNuevos) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente->{
                    usuarioExistente.setNombre(datosNuevos.getNombre());
                    usuarioExistente.setApellido(datosNuevos.getApellido());
                    usuarioExistente.setEmail(datosNuevos.getEmail());
                    usuarioExistente.setFechaNacimiento(datosNuevos.getFechaNacimiento());
                    return usuarioRepository.save(usuarioExistente);
                }).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado o inactivo con el ID: " + id));
    }

    @Transactional
    public void eliminarUsuario(Long id) {
      if(!usuarioRepository.existsById(id)){
          throw new IllegalArgumentException("No se puede eliminar. Usuario no encontrado o ya inactivo con el ID: " + id);
      }
      usuarioRepository.deleteById(id);

    }

}
