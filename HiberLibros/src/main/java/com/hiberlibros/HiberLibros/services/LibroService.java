/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.interfaces.LibroServiceI;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class LibroService implements LibroServiceI{
    @Autowired
    private LibroRepository libroRep;

    @Override
    public List<Libro> buscarLibro(String libro) {
        return libroRep.findByIsbnContainsOrTituloContainsIgnoreCase(libro, libro);
    }

    @Override
    public Libro libroId(Integer id) {
       return libroRep.findById(id).get();
    }

    @Override
    public void guardarLibro(Libro l) {
        libroRep.save(l);
    }
    
}
