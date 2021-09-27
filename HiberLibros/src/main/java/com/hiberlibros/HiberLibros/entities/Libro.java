package com.hiberlibros.HiberLibros.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String isbn;
    private String titulo;
    private String idioma;
    private String uriPortada;
    private Double valoracionLibro;
    private Integer numeroValoraciones;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_genero")
    private Genero genero;

}
