package com.pruebabiblioteca.fullstack.service;


import com.pruebabiblioteca.fullstack.model.*;
import com.pruebabiblioteca.fullstack.repository.EjemplarRepository;
import com.pruebabiblioteca.fullstack.repository.PrestamoRepository;
import com.pruebabiblioteca.fullstack.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, EjemplarRepository ejemplarRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Prestamo registrarPrestamo(Long usuarioId, Long ejemplarId, int diasPrestamo) {

        boolean tienePrestamoActivo = prestamoRepository.existsByUsuarioIdAndEstadoPrestamoIn(
                usuarioId, List.of(EstadoPrestamo.ACTIVO, EstadoPrestamo.VENCIDO)
        );
        if (tienePrestamoActivo) {
            throw new IllegalArgumentException("El usuario ya tiene un préstamo activo o pendiente de devolución.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Ejemplar ejemplar = ejemplarRepository.findById(ejemplarId)
                .orElseThrow(() -> new IllegalArgumentException("Ejemplar no encontrado"));

        if (ejemplar.getEstado() != EstadoEjemplar.DISPONIBLE) {
            throw new IllegalStateException("El ejemplar seleccionado no está disponible");
        }

        ejemplar.setEstado(EstadoEjemplar.PRESTADO);
        ejemplarRepository.save(ejemplar);

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(diasPrestamo));
        prestamo.setEstadoPrestamo(EstadoPrestamo.ACTIVO);

        return prestamoRepository.save(prestamo);
    }
    public List<Prestamo> listarPorUsuario(Long usuarioId) {
        List<Prestamo> prestamos = prestamoRepository.findByUsuarioId(usuarioId);
        prestamos.forEach(this::actualizarEstado);
        return prestamos;
    }

    public List<Prestamo> listarPorLibro(Long libroId) {
        List<Prestamo> prestamos = prestamoRepository.findByEjemplarId(libroId);
        prestamos.forEach(this::actualizarEstado);
        return prestamos;
    }

    public List<Ejemplar> listarEjemplaresDisponibles(String isbn) {
        return ejemplarRepository.findByLibroIsbnAndEstado(isbn, EstadoEjemplar.DISPONIBLE);
    }

    private void actualizarEstado(Prestamo prestamo) {
        if (prestamo.getEstadoPrestamo() == EstadoPrestamo.ACTIVO &&
                LocalDate.now().isAfter(prestamo.getFechaDevolucion())) {
            prestamo.setEstadoPrestamo(EstadoPrestamo.VENCIDO);
            prestamoRepository.save(prestamo);
        }
    }
}
