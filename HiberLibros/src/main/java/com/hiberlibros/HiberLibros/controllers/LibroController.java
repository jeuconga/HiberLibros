/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Editorial;
import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
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
    @Autowired
    private AutorRepository AutRepo;

 
    @GetMapping("/libros")
    public String mostrarFormulario(Model m){
        m.addAttribute("libros", librepo.findAll());
        m.addAttribute("generos", genRepo.findAll());
        m.addAttribute("editoriales", editRepo.findAll());
        m.addAttribute("autores", AutRepo.findAll());
        return "libros/VistaLibro";
    } 
    
    @PostMapping("/guardar")
    public String guardarLIbro(Model m,Libro libro, Integer id_genero, Integer id_editorial,Integer id_autor){
        libro.setGenero(genRepo.getById(id_genero));
        libro.setEditorial(editRepo.getById(id_genero));
        libro.setAutor(AutRepo.getById(id_autor));
        librepo.save(libro);
        return "redirect:libros";
    }
    
  
    
    @GetMapping("/eliminar")
    public String eliminarLibro(Model m,Integer id){
        Optional<Libro> l =librepo.findById(id);
        if(l.isPresent()){
            librepo.deleteById(id);           
        }
        return "redirect:libros";
    }
    
    @GetMapping("/modificar")
    public String modificarLibro(Model m,Integer id){
       
       m.addAttribute("libro", librepo.findById(id));
       m.addAttribute("generos", genRepo.findAll());
       m.addAttribute("editoriales", editRepo.findAll());
       m.addAttribute("autores", AutRepo.findAll());
       return "libros/modificar";
    }
}
