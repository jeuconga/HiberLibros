package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface EditorialRepository extends JpaRepository<Editorial, Integer> {

    public List<Editorial> findByNombreEditorialIgnoreCase(String nombreEditorial);
}
