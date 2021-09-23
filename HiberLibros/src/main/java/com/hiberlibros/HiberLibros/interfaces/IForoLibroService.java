package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.ForoLibro;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IForoLibroService {

    public List<ForoLibro> recuperarForosDeLibro(Integer idLibro);

    public List<ForoLibro> recuperarTodosLosForos();

    public void altaForoLibro(ForoLibro l);
}
