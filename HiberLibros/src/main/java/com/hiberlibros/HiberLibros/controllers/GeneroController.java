package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.dtos.GeneroDto;
import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Isabel
 */
@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public String verGeneros(Model model) {
        model.addAttribute("generos", generoRepository.findAll());
        model.addAttribute("generoForm", new Genero());

        return "/generos/genero";
    }

    @PostMapping("/guardar")
    public String formulario(Genero genero) {
        generoRepository.save(genero);

        return "redirect:listarAdmin";
    }

    @GetMapping("/borrar/{id}")
    public String borrarGenero(@PathVariable Integer id) {
        generoRepository.deleteById(id);

        return "redirect:/hiberlibros/paneladmin";
    }

    @GetMapping("/editar")
    @ResponseBody
    public GeneroDto editarGenero(Integer id) {
        Genero editGenero = generoRepository.getById(id);
        GeneroDto genDto = new GeneroDto(editGenero.getId(), editGenero.getNombre());
   
//        model.addAttribute("genero", editGenero);

        return genDto;
    }

    @GetMapping("/listarAdmin")
    private String listarTodo(Model m) {
        m.addAttribute("generos", generoRepository.findAll());
        m.addAttribute("generoForm", new Genero());
        return "/administrador/generos";
    }
}