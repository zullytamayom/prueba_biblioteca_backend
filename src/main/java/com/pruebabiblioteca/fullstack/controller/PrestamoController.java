package com.pruebabiblioteca.fullstack.controller;

import com.pruebabiblioteca.fullstack.dto.PrestamoRequest;
import com.pruebabiblioteca.fullstack.dto.PrestamoResponse;
import com.pruebabiblioteca.fullstack.model.Ejemplar;
import com.pruebabiblioteca.fullstack.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarPrestamo(@RequestBody PrestamoRequest request) {
        try {
            var prestamo = prestamoService.registrarPrestamo(
                    request.getUsuarioId(),
                    request.getEjemplarId(),
                    request.getDiasPrestamo()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(PrestamoResponse.fromEntity(prestamo));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PrestamoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<PrestamoResponse> respuestas = prestamoService.listarPorUsuario(usuarioId)
                .stream()
                .map(PrestamoResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<PrestamoResponse>> listarPorLibro(@PathVariable Long libroId) {
        List<PrestamoResponse> respuestas = prestamoService.listarPorLibro(libroId)
                .stream()
                .map(PrestamoResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/ejemplares-disponibles")
    public ResponseEntity<List<Ejemplar>> listarEjemplaresDisponibles(@RequestParam String isbn) {
        return ResponseEntity.ok(prestamoService.listarEjemplaresDisponibles(isbn));
    }
}
