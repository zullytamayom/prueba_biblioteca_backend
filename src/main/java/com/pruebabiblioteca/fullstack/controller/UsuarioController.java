package com.pruebabiblioteca.fullstack.controller;

import com.pruebabiblioteca.fullstack.model.Usuario;
import com.pruebabiblioteca.fullstack.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

    }

    @GetMapping
    public ResponseEntity<List<Usuario>>listarUsuarios(){
        List<Usuario> usuarios = usuarioService.usuarioList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.usuarioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDetalles);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
