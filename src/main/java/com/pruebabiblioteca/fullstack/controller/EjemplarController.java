package com.pruebabiblioteca.fullstack.controller;


import com.pruebabiblioteca.fullstack.dto.EjemplarRequest;
import com.pruebabiblioteca.fullstack.dto.EjemplarResponse;
import com.pruebabiblioteca.fullstack.model.Ejemplar;
import com.pruebabiblioteca.fullstack.service.EjemplarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ejemplares")
public class EjemplarController {

    private final EjemplarService ejemplarService;

    public EjemplarController(EjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
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
}
