
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isabel
 */
public interface IPreferenciaService {

    //Todas las preferencias
    List<Preferencia> findAll();
    
    //Preferencias por usuario
    public List<Preferencia> findByUsuario(Usuario usuario);
    
    //Añadir preferencia
    Preferencia addPreferencia (Preferencia preferencia);
    
     //Borrar preferencia
    public void borrarPreferencia(Integer id);
    
}
