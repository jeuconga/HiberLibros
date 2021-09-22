
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Genero;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;

/**
 *
 * @author Isabel
 */
@Service
public class GeneroService implements IGeneroService {
    
    private List<Genero> listaGeneros = new ArrayList<>();

    @Override
    public void guardarGenero(Genero genero) {
        listaGeneros.add(genero);
    }

    @Override
    public void borrarGenero(Integer id) {

    }

    @Override
    public List<Genero> getGeneros() {
        return listaGeneros;
    }
}
