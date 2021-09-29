package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Editorial;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class EditorialService {

    @Autowired
    private EditorialRepository repoEditorial;

    public List<Editorial> consultaTodas() {
        return repoEditorial.findAll();
    }

    public void altaModificacionEditorial(Editorial editorial) {
        editorial.setDesactivado(Boolean.FALSE);
        repoEditorial.save(editorial);
    }

    public void bajaEditorial(int idEditorial) {
        repoEditorial.deleteById(idEditorial);
    }

    public Editorial consultaPorIdEditorial(int idEditorial) {
        Optional<Editorial> editorial = repoEditorial.findById(idEditorial);
        if (editorial.isPresent()) {
            return editorial.get();
        } else {
            return null;
        }
    }

    public List<Editorial> consultaPorNombreEditorial(Editorial editorial) {
        return repoEditorial.findByNombreEditorialIgnoreCase(editorial.getNombreEditorial());
    }

}
