/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroRepository librepo;
    
    @GetMapping("/libros")
    public String mostrarFormulario(Model m){
        m.addAttribute("libros", librepo.findAll());
        return "VistaLibro";
    } 
    
    @PostMapping("/guardarLibro")
    public String guardarLIbro(Model m,Libro libro){
        librepo.save(libro);
        return "redirect:libros";
    }
    
    @GetMapping("/eliminar")
    public String eliminarLibro(Model m,Integer id){
        Optional<Libro> l =librepo.findById(id);
        if(l.isPresent()){
            librepo.deleteById(id);           
        }
        return "redirect:vistaLibro";
    }
    
    @GetMapping("/modificar")
    public String modificarLibro(Model m,Integer id){
       m.addAttribute("libro", librepo.findById(id));
       return "redirect:vistaLibro";
    }
}
