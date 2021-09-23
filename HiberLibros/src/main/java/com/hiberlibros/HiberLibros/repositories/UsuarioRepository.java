package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Usuario
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Modifying
    @Query("update Usuario u set u.desactivado = true where u.id = :id")
    public void borradoLogico(Integer id);

    public List<Usuario> findByDesactivado(Boolean b);

    public Optional<Usuario> findByMailContainsIgnoreCase(String mail);

    public Optional<Usuario> findByMail(String mail);

}
