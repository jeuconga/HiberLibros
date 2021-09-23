package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.ForoLibro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface ForoLibroRepository extends JpaRepository<ForoLibro, Integer> {

    public List<ForoLibro> findByIdLibro(Integer idLibro);
}
