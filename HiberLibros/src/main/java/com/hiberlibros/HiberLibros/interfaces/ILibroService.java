package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ILibroService {

    public List<Libro> buscarLibro(String libro);

    public Libro libroId(Integer id);

    public void guardarLibro(Libro l);

    public Integer contarLibros();

    public List<Libro> encontrarPorAutor(Autor a);

    public void valorarLibro(Libro l, Integer valoracion);
}
