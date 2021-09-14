/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Editorial;
import com.hiberlibros.HiberLibros.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("")
public class EditorialController {
    @Autowired
    private EditorialService serviceEditorial;
    
    @GetMapping("/editoriales")
    public String editoriales(Model m,Editorial editorial){
        if (editorial == null){
            editorial = new Editorial();
        }
        m.addAttribute("editorial", editorial);
        m.addAttribute("editoriales",serviceEditorial.consulta());
        return "/editoriales";
    }
}
