package com.pruebabiblioteca.fullstack.dto;

import com.pruebabiblioteca.fullstack.model.EstadoPrestamo;
import com.pruebabiblioteca.fullstack.model.Prestamo;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PrestamoResponse {
    @Column(name = "id_prestamo")
    private Long idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private EstadoPrestamo estadoPrestamo;
    private Long usuarioId;
    private String nombreUsuario;
    private Long ejemplarId;
    private String codigoEjemplar;
    private String tituloLibro;

    public static PrestamoResponse fromEntity(Prestamo prestamo) {
        PrestamoResponse dto = new PrestamoResponse();
        dto.setIdPrestamo(prestamo.getIdPrestamo());
        dto.setFechaPrestamo(prestamo.getFechaPrestamo());
        dto.setFechaDevolucion(prestamo.getFechaDevolucion());
        dto.setEstadoPrestamo(prestamo.getEstadoPrestamo());

        if (prestamo.getUsuario() != null) {
            dto.setUsuarioId(prestamo.getUsuario().getIdUsuario());
            dto.setNombreUsuario(prestamo.getUsuario().getNombre() + " " + prestamo.getUsuario().getApellido());
        }

        if (prestamo.getEjemplar() != null) {
            dto.setEjemplarId(prestamo.getEjemplar().getIdEjemplares());
            dto.setCodigoEjemplar(prestamo.getEjemplar().getCodigoEjemplar());
            if (prestamo.getEjemplar().getLibro() != null) {
                dto.setTituloLibro(prestamo.getEjemplar().getLibro().getTitulo());
            }
        }

        return dto;
    }
}
