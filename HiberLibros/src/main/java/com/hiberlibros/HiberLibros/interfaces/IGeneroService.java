
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Genero;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Isabel
 */
public interface IGeneroService {
    
    //Añadir género
    public void guardarGenero(Genero genero);
    
    //Borrar género
    public void borrarGenero(Integer id);
    
    //Listar géneros
    public List<Genero> getGeneros();
    
    //Buscar información por id
    public Optional<Genero> getGenero(Integer id);
     
    
    
    
}
