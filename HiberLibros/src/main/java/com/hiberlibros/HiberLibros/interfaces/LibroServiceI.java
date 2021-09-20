/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Libro;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface LibroServiceI {
    
    public List<Libro> buscarLibro(String libro);
    public Libro libroId(Integer id);
    public void guardarLibro(Libro l);
    
}
