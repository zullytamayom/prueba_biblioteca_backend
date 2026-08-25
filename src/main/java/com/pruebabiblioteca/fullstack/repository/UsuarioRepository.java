package com.pruebabiblioteca.fullstack.repository;

import com.pruebabiblioteca.fullstack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
