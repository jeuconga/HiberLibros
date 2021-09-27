/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.ComentarioForo;
import com.hiberlibros.HiberLibros.interfaces.IComentarioForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class ComentarioForoSerice implements IComentarioForoService{

    @Autowired
    private IComentarioForoService serviceComentarioForoService;
    
    @Override
    public void altaComentario(ComentarioForo c) {
        serviceComentarioForoService.altaComentario(c);
    }

    @Override
    public void bajaComentario(Integer idComentario) {
        serviceComentarioForoService.bajaComentario(idComentario);
    }
    
}
