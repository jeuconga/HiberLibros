/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Editorial;
import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroRepository librepo;
    @Autowired
    private GeneroRepository genRepo;
    @Autowired
    private EditorialRepository editRepo;
    
    @GetMapping("/generarDatos")
    public String insertarEjemplo(Model m){
        Genero g=new Genero(null,"fantasia",null,null);
        Editorial editorial=new Editorial(null,"Anaya");
        editRepo.save(editorial);
        genRepo.save(g);
        Libro l=new Libro(null, "2496706464", "Todo tiene su precio", "Spanish","https://images-na.ssl-images-amazon.com/images/I/41qkoU0+hsS._SX331_BO1,204,203,200_.jpg",4.5,editorial,g);

        librepo.save(l);

        return "redirect:libros";    
    }  
    
    
    @GetMapping("/libros")
    public String mostrarFormulario(Model m){
        m.addAttribute("libros", librepo.findAll());
        m.addAttribute("generos", genRepo.findAll());
        return "VistaLibro";
    } 
    
    @PostMapping("/guardarLibro")
    public String guardarLIbro(Model m,Libro libro, Integer id_genero, Integer id_editorial){
        libro.setGenero(genRepo.getById(id_genero));
        libro.setEditorial(editRepo.getById(id_genero));
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
