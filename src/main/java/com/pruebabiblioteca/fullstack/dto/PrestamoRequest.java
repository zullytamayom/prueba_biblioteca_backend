package com.pruebabiblioteca.fullstack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoRequest {
    private Long usuarioId;
    private Long ejemplarId;
    private int diasPrestamo = 7;
}
