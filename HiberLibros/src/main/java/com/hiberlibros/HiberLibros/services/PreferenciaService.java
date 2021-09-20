
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.interfaces.PreferenciaServiceI;
import com.hiberlibros.HiberLibros.repositories.PreferenciaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class PreferenciaService implements PreferenciaServiceI {
    
    @Autowired
    private PreferenciaRepository prefRepo;
    
    private List<Preferencia> listaPreferencias = new ArrayList<>();

    @Override
    public List<Preferencia> getPreferencias() {
        return listaPreferencias;
    }

    @Override
    public Preferencia buscaId(Integer id) {
        return prefRepo.findById(id).get();
    }

    @Override
    public List<Preferencia> buscaUsuario(Usuario usuario) {
        return prefRepo.findByUsuario(usuario);
    }
    
}
