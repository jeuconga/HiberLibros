/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.interfaces.IForoLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/foros")
public class ForoLibroController {

    @Autowired
    private IForoLibroService serviceForoLibro;

    @GetMapping("/libro")
    public String recuperarForos (Integer idLibro){
    
        return "/principal/foro";
    }
}
