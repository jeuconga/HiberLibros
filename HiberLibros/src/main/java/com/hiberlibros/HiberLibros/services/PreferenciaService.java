
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.repositories.PreferenciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.interfaces.IPreferenciaService;
import com.hiberlibros.HiberLibros.repositories.UsuarioRepository;

/**
 *
 * @author Isabel
 */
@Service
public class PreferenciaService implements IPreferenciaService {
    
    @Autowired
    private PreferenciaRepository prefRepo;
    @Autowired
    private UsuarioRepository usuRepo;

    @Override
    public List<Preferencia> findByUsuario(Integer idUsuario) {
        return prefRepo.findByUsuario(usuRepo.findById(idUsuario).get());   
    } 

    @Override
    public List<Preferencia> findAll() {
        return prefRepo.findAll();       
    }

    @Override
    public Preferencia addPreferencia(Preferencia preferencia) {
        Preferencia nuevaPref = new Preferencia();
        nuevaPref.setGenero(preferencia.getGenero());
        nuevaPref.setAutor(preferencia.getAutor());
        nuevaPref.setUsuario(preferencia.getUsuario());
        
        return prefRepo.save(nuevaPref);
    }
}
