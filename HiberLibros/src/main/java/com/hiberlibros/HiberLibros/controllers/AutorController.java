package com.hiberlibros.HiberLibros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;

import lombok.Setter;

@Controller
@RequestMapping("")
public class AutorController {
	
	@Setter
	@Autowired(required = false)
    private AutorRepository autorRepo;
	
	@GetMapping
	public String lista(Model m){
		m.addAttribute("autores", autorRepo.findAll());
		return "autores/lista";
	}
	@GetMapping("/autorForm")
	public String read(Model m){
		m.addAttribute("autor", new Autor());
		return "autores/autorForm";
	}
	@PostMapping("/save")
	public String insertarAutor(Model m,Autor autor){
		autorRepo.save(autor);
		return "redirect:autores/lista";
	}
}
