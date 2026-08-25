package com.pruebabiblioteca.fullstack.repository;

import com.pruebabiblioteca.fullstack.model.Ejemplar;
import com.pruebabiblioteca.fullstack.model.EstadoEjemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar,Long> {

    List<Ejemplar> findByLibroIsbnAndEstado(String isbn, EstadoEjemplar estado);
}
