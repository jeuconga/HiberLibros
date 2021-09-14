/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entitis.Editorial;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Usuario
 */
public class EditorialService {
    @Autowired
    private EditorialRepository repoEditorial;
    
    public List<Editorial> consulta(){
        return repoEditorial.findAll();
    }
    
}
