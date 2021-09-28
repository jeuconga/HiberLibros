package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface UsuarioLibroRepository extends JpaRepository<UsuarioLibro, Integer> {

    public List<UsuarioLibro> findByUsuario(Usuario u);

    public List<UsuarioLibro> findByUsuarioAndQuieroTengoAndEstadoPrestamo(Usuario u, String quieroTengo, String estadoPrestamo);

    public List<UsuarioLibro> findByLibroAndQuieroTengoAndEstadoPrestamo(Libro l, String quieroTengo, String estadoPrestamo);

    public List<UsuarioLibro> findByUsuarioAndQuieroTengoNotOrderByQuieroTengoAsc(Usuario u, String quieroTengo);

    public List<UsuarioLibro> findByUsuarioNotAndQuieroTengoAndEstadoPrestamo(Usuario u, String quieroTengo, String estadoPrestamo);
    
    public Long countByUsuario(Usuario usuario );

}
