package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mohamad
 */
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    public List<Libro> findByIsbnContainsOrTituloContainsIgnoreCase(String isbn, String titulo);

    public List<Libro> findByAutor(Autor autor);
}
