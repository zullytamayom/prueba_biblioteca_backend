package com.pruebabiblioteca.fullstack.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EjemplarRequest {
    private String codigoEjemplar;
    private Long libroId;
}
