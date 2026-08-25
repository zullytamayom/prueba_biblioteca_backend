package com.pruebabiblioteca.fullstack.repository;

import com.pruebabiblioteca.fullstack.model.EstadoPrestamo;
import com.pruebabiblioteca.fullstack.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo,Long> {

    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByEjemplarId(Long ejemplarId);
    boolean existsByUsuarioIdAndEstadoPrestamoIn(Long usuarioId, Collection<EstadoPrestamo> estadoPrestamo);
}
