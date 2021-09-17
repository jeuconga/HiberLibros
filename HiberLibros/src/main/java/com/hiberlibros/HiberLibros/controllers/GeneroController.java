package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.interfaces.IGeneroService;
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
    private GeneroRepository generoRepository;
    @Autowired
    private IGeneroService generoService;
    
    
    @GetMapping
    public String verGeneros(Model model){
        model.addAttribute("generos", generoRepository.findAll());
        model.addAttribute("generoForm", new Genero());
        return "/generos/genero";
    }
    
    @PostMapping("/guardar")
    public String formulario(Genero genero){
        generoRepository.save(genero);
        return "redirect:/genero";
    }
    
    @GetMapping("/borrar/{id}")
    public String borrarGenero(@PathVariable Integer id){
        generoRepository.deleteById(id);
        
        return "redirect:/genero";
    }
    
    @GetMapping("/editar/{id}")
    public String editarGenero(Model model, @PathVariable Integer id){
        Genero editGenero = generoService.getGenero(id).get();
        model.addAttribute("genero", editGenero);
        
        return "/generos/editaGenero";
    }
}
