package com.pruebabiblioteca.fullstack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="libros")
@SQLDelete(sql = "UPDATE libros SET activo = false WHERE id = ?")
@SQLRestriction("activo = true")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String isbn;

    private String edicion;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    private String autor;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ejemplar> ejemplares;

    @Column(nullable = false)
    private boolean activo = true;
}
