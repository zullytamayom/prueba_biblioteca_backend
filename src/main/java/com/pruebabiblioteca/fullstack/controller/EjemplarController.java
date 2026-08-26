package com.pruebabiblioteca.fullstack.controller;


import com.pruebabiblioteca.fullstack.dto.EjemplarRequest;
import com.pruebabiblioteca.fullstack.dto.EjemplarResponse;
import com.pruebabiblioteca.fullstack.model.Ejemplar;
import com.pruebabiblioteca.fullstack.service.EjemplarService;
import com.pruebabiblioteca.fullstack.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
public class EjemplarController {

    private final EjemplarService ejemplarService;
    private final PrestamoService prestamoService;

    public EjemplarController(EjemplarService ejemplarService, PrestamoService prestamoService) {
        this.ejemplarService = ejemplarService;
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<?> crearEjemplar(@RequestBody EjemplarRequest request) {
        try {
            Ejemplar ejemplarGuardado = ejemplarService.crearEjemplar(
                    request.getCodigoEjemplar(),
                    request.getLibroId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(EjemplarResponse.fromEntity(ejemplarGuardado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<EjemplarResponse>> listarEjemplaresDisponibles(@RequestParam String isbn) {
        List<EjemplarResponse> respuestas = prestamoService.listarEjemplaresDisponibles(isbn)
                .stream()
                .map(EjemplarResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(respuestas);
    }


}
