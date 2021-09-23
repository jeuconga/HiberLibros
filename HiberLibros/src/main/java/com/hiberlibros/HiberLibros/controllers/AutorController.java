package com.hiberlibros.HiberLibros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hiberlibros.HiberLibros.dtos.LibroDto;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;

import lombok.Setter;

@Controller
@RequestMapping
public class AutorController {

	@Autowired(required = false)
	private IAutorService autorService;

	@GetMapping("/autorLista")
	public String lista(Model m){
		m.addAttribute("autores", autorService.consultarAutores());
		return "autores/lista";
	}

	@GetMapping("/autorForm")
	public String read(Model m){
		m.addAttribute("autor", new Autor());
		return "autores/autorForm";
	}

	@GetMapping("/autorForm/{id}")
	public String find(Model m,@PathVariable Integer id){
		m.addAttribute("autor", autorService.encontrarAutor(id));
		return "autores/autorForm";
	}
	@PostMapping("/saveAutor")
	public String insertarAutor(Autor autor){
		autorService.guardarAutor(autor);
		return "redirect:autorLista";
	}

	@GetMapping("/deleteAutor/{id}")
	public String delete(@PathVariable Integer id){
		autorService.borrarAutor(id);
		return "redirect:/autorLista";
	}

	@GetMapping("/getLibrosAutor")
	@ResponseBody
	public List<LibroDto> consultarLibros(Integer id){
		return autorService.getLibros(id);
	}

	@GetMapping("/buscarAutor")
	public String buscarAutores(Model m,String buscador){
		m.addAttribute("buscador", buscador);
		if (buscador == null) {
			m.addAttribute("autores", autorService.consultarAutores());
		} else {
			m.addAttribute("autores", autorService.buscarAutores(buscador));
		}
		return "autores/lista";
	}
}
