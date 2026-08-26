package com.pruebabiblioteca.fullstack.service;

import com.pruebabiblioteca.fullstack.model.Ejemplar;
import com.pruebabiblioteca.fullstack.model.EstadoEjemplar;
import com.pruebabiblioteca.fullstack.model.Libro;
import com.pruebabiblioteca.fullstack.repository.EjemplarRepository;
import com.pruebabiblioteca.fullstack.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class EjemplarService {
    private final EjemplarRepository ejemplarRepository;
    private final LibroRepository libroRepository;

    public EjemplarService(EjemplarRepository ejemplarRepository, LibroRepository libroRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.libroRepository = libroRepository;
    }

    public Ejemplar crearEjemplar(String codigoEjemplar, Long libroId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new IllegalArgumentException("El libro con ID " + libroId + " no existe."));

        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setCodigoEjemplar(codigoEjemplar);
        ejemplar.setEstado(EstadoEjemplar.DISPONIBLE);
        ejemplar.setLibro(libro);

        return ejemplarRepository.save(ejemplar);
    }
}
