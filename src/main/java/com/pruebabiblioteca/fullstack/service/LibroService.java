package com.pruebabiblioteca.fullstack.service;

import com.pruebabiblioteca.fullstack.model.Libro;
import com.pruebabiblioteca.fullstack.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    @Transactional
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listaLibros() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Libro> libroById(Long id) {
        return libroRepository.findById(id);
    }

    @Transactional
    public Libro actualizarLibro(Long id,Libro DatosActualizados) {
        return libroRepository.findById(id)
                .map(libroExistente->
                {
                   libroExistente.setIdLibro(DatosActualizados.getIdLibro());
                   libroExistente.setEdicion(DatosActualizados.getEdicion());
                   libroExistente.setAutor(DatosActualizados.getAutor());
                   libroExistente.setFechaPublicacion(DatosActualizados.getFechaPublicacion());
                   libroExistente.setIsbn(DatosActualizados.getIsbn());
                   libroExistente.setTitulo(DatosActualizados.getTitulo());
                   return libroRepository.save(libroExistente);
                }).orElseThrow(()-> new IllegalArgumentException("Libro no encontrado o inactivo con el ID: " + id));

    }

    @Transactional
    public void deleteLibro(Long id){
        if(!libroRepository.existsById(id)){
            throw new IllegalArgumentException("No se puede eliminar. Libro no encontrado o ya inactivo con el ID: " + id);
        }
        libroRepository.deleteById(id);
    }
}
