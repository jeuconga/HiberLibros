/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Mohamad
 */
@Controller
public class AdministradorControlller {
    
    @Autowired
    private LibroRepository librepo;
    @Autowired
    private GeneroRepository genRepo;
    @Autowired
    private EditorialRepository editRepo;
    @Autowired
    private AutorRepository AutRepo;

    @GetMapping("/admin")
    public String adminHub(Model m){
        return "administrador/vistaAdministrador";
    }
    
     @GetMapping("/gestionlibros")
    public String crudLibros(Model m){
        m.addAttribute("libros", librepo.findAll());
        m.addAttribute("generos", genRepo.findAll());
        m.addAttribute("editoriales", editRepo.findAll());
        m.addAttribute("autores", AutRepo.findAll());
        System.out.println("autor " + AutRepo.findAll() );
        return "administrador/vistaLibro";
    }
}
