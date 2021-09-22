/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.ForoLibro;
import com.hiberlibros.HiberLibros.interfaces.IForoLibroService;
import com.hiberlibros.HiberLibros.repositories.ForoLibroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class ForoLibroService implements IForoLibroService{
    @Autowired
    private ForoLibroRepository repoForoLibro;
    @Override
    
    
    public List<ForoLibro> recuperarForosDeLibro(Integer idLibro) {
        return repoForoLibro.findByIdLibro(idLibro);
    }
    
    public List<ForoLibro> recuperarTodosLosForos(){
        return repoForoLibro.findAll();
    }

    @Override
    public void altaForoLibro(ForoLibro l) {
            repoForoLibro.save(l);
    }
    
}
