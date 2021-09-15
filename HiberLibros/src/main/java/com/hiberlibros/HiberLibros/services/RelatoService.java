/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import com.hiberlibros.HiberLibros.entities.Relato;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



public class RelatoService {

    @Autowired
    private RelatoRepository repoRelato;

    public List<Relato> consulta() {
        return repoRelato.findAll();
    }

}
