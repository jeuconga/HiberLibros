
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface PreferenciaRepository extends JpaRepository<Preferencia, Integer> {
    
    //Listado de preferencias por usuario
    List<Preferencia> findByUsuario(Usuario usuario);
    
}
