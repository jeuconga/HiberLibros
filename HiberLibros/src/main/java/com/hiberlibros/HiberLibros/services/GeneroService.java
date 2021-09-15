
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        listaGeneros.removeIf(x -> x.getId() == id);
    }

    @Override
    public List<Genero> getGeneros() {
        return listaGeneros;
    }
}
