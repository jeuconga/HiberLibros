/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository librepo;
    @Autowired
    private GeneroRepository genRepo;
    @Autowired
    private EditorialRepository editRepo;
    @Autowired
    private AutorRepository AutRepo;
    @Autowired
    private ILibroService libroService;


    @GetMapping("/libros")

    public String mostrarFormulario(Model m) {
        m.addAttribute("libros", librepo.findAll());
        m.addAttribute("generos", genRepo.findAll());
        m.addAttribute("editoriales", editRepo.findAll());
        m.addAttribute("autores", AutRepo.findAll());

        System.out.println("autor " + AutRepo.findAll());

        return "libros/VistaLibro";
    }

    @PostMapping("/guardar")
    public String guardarLIbro(Model m, Libro libro, Integer id_genero, Integer id_editorial, Integer id_autor) {
        libro.setGenero(genRepo.getById(id_genero));
        libro.setEditorial(editRepo.getById(id_genero));
        libro.setAutor(AutRepo.getById(id_autor));
        librepo.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/eliminar")
    public String eliminarLibro(Model m, Integer id) {
        Optional<Libro> l = librepo.findById(id);
        if (l.isPresent()) {
            librepo.deleteById(id);
        }
        return "redirect:libros";
    }

    @GetMapping("/modificar")
    public String modificarLibro(Model m, Integer id) {
        m.addAttribute("libro", librepo.findById(id));
        m.addAttribute("generos", genRepo.findAll());
        m.addAttribute("editoriales", editRepo.findAll());
        m.addAttribute("autores", AutRepo.findAll());
        return "libros/modificar";

    }

    @GetMapping("/listarAdmin")
    private String listarTodo(Model m) {
        m.addAttribute("libros", librepo.findAll());
        m.addAttribute("generos", genRepo.findAll());
        m.addAttribute("editoriales", editRepo.findAll());
        m.addAttribute("autores", AutRepo.findAll());
        return "/administrador/libros";
    }

    @PostMapping("/guardarAdmin")
    public String guardarAdmin(Model m, Libro libro, Integer id_genero, Integer id_editorial, Integer id_autor) {
        libro.setGenero(genRepo.getById(id_genero));
        libro.setEditorial(editRepo.getById(id_genero));
        libro.setAutor(AutRepo.getById(id_autor));
        librepo.save(libro);
        return "/administrador/vistaAdministrador";

    }

    
      @GetMapping("/eliminarAdmin")
    public String eliminarAdmin(Model m, Integer id) {
        Optional<Libro> l = librepo.findById(id);
        if (l.isPresent()) {
            librepo.deleteById(id);
        }
        return "/administrador/vistaAdministrador";
    }



    @PostMapping("/addValoracionLibro")
    public String addValoracionLibro(Model m, Integer id, Integer valoracion) {
        Optional<Libro> l = librepo.findById(id);
        if (l.isPresent()) {
            libroService.valorarLibro(l.get(), valoracion);
        }
        return "redirect:/hiberlibros/buscarLibro";
    }

}
 
