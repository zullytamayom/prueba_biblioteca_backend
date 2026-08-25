package com.pruebabiblioteca.fullstack.dto;

import com.pruebabiblioteca.fullstack.model.Ejemplar;
import com.pruebabiblioteca.fullstack.model.EstadoEjemplar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EjemplarResponse {
    private Long id;
    private String codigoInventario;
    private EstadoEjemplar estado;
    private Long libroId;
    private String tituloLibro;

    public static EjemplarResponse fromEntity(Ejemplar ejemplar) {
        EjemplarResponse dto = new EjemplarResponse();
        dto.setId(ejemplar.getId());
        dto.setCodigoInventario(ejemplar.getCodigoEjemplar());
        dto.setEstado(ejemplar.getEstado());

        if (ejemplar.getLibro() != null) {
            dto.setLibroId(ejemplar.getLibro().getId());
            dto.setTituloLibro(ejemplar.getLibro().getTitulo());
        }

        return dto;
    }
}
