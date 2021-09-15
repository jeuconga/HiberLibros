package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Isabel
 */
@Controller
@RequestMapping("/genero")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;
    
    @Autowired
    private GeneroRepository generoRepository;
    
    
    @GetMapping("/ver")
    public String verGeneros(Model model){
        model.addAttribute("generos", generoRepository.findAll());
        return "genero";
        
    }
    
    @PostMapping("/guardar")
    public String formulario(Model model, Genero genero){
        generoService.guardarGenero(genero);
 
        return "redirect:ver";
    }
    
    @GetMapping("/borrar/{id}")
    public String borrarGenero(@PathVariable Integer id){
        generoService.borrarGenero(id);
        
        return "/genero";
    }
    
    
}
