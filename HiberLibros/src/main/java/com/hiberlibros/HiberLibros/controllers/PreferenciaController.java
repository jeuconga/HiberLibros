/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.PreferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.interfaces.IPreferenciaService;
import com.hiberlibros.HiberLibros.services.PreferenciaService;

/**
 *
 * @author Isabel
 */
@Controller
@RequestMapping("preferencia")
public class PreferenciaController {
    
    @Autowired
    private PreferenciaService prefService;     
    @Autowired
    private GeneroRepository genRepo;
    @Autowired
    private AutorRepository autorRepo;
    
    @GetMapping
    public String verPreferencias(Model model){
        model.addAttribute("preferencias", prefService.findAll());
        model.addAttribute("generos", genRepo.findAll());
        model.addAttribute("autores", autorRepo.findAll());
        
        
        return "/preferencias/preferencia";
    }
    
    @PostMapping("/anadir")
    public String anadirPreferencia(Model model, Preferencia preferencia){
    prefService.addPreferencia(preferencia);
    
    return "redirect: preferencia";
    }
    
    
    
    
}
