package com.pruebabiblioteca.fullstack.repository;

import com.pruebabiblioteca.fullstack.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
