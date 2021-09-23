package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Intercambio;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface IntercambioRepository extends JpaRepository<Intercambio, Integer> {

    public List<Intercambio> findByUsuarioPrestador(UsuarioLibro ul);

    public List<Intercambio> findByUsuarioPrestatario(UsuarioLibro ul);
}
