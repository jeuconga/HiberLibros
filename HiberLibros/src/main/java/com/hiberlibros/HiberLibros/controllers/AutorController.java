package com.hiberlibros.HiberLibros.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper; 
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
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.services.AutorService;

import lombok.Setter;

@Controller
@RequestMapping
public class AutorController {

	
	@Autowired
	private AutorRepository autorRepo;
	
	@Autowired
	private AutorLibroRepository repo;
	
	@Autowired
	private AutorService autorService;
        @Autowired
        private AutorLibroRepository alrepo;
        @Autowired
        private ILibroService ilibroservice;
	
    @Autowired
    private ModelMapper obj;

	@GetMapping("/autorLista")
	public String lista(Model m){
		m.addAttribute("autores", autorRepo.findAll());
		return "autores/lista";
	}
	@GetMapping("/autorForm")
	public String read(Model m){
		m.addAttribute("autor", new Autor());
		return "autores/autorForm";
	}
	@GetMapping("/autorForm/{id}")
	public String find(Model m,@PathVariable Integer id){
		m.addAttribute("autor", autorRepo.findById(id));
		return "autores/autorForm";
	}
	@PostMapping("/saveAutor")
	public String insertarAutor(Autor autor){
		autorRepo.save(autor);
		return "redirect:autorLista";
	}
	@GetMapping("/deleteAutor/{id}")
	public String delete(@PathVariable Integer id){
		autorRepo.deleteById(id);
		return "redirect:/autorLista";
	}
	@GetMapping("/getLibrosAutor")
	@ResponseBody
	public List<LibroDto> getLibros(Integer id){
        return (List<LibroDto>) repo.findAll()
                .stream()
                .filter(z -> z.getAutor().getIdAutor() == id)
                .map(x-> obj.map(x.getLibro(), LibroDto.class))
                .collect(Collectors.toList());

	}
        
        
	@GetMapping("/buscarAutor")
	public String buscarAutores(Model m,String buscador){
        m.addAttribute("buscador", buscador);
		if (buscador == null) {
            m.addAttribute("autores", autorRepo.findAll());
        } else {
            m.addAttribute("autores", autorService.consultarAutores(buscador));
        }
        return "autores/lista";
	}
        
        @GetMapping("/autores/listarAdmin")
	public String listaAdmin(Model m){
		m.addAttribute("autores", autorRepo.findAll());
		return "administrador/autores";
	}
        
        @GetMapping("/librosAutor")
	public String LibrosDeAutor(Model m,Integer id){
            Autor a=autorRepo.findById(id).get();
            
            m.addAttribute("libros",ilibroservice.encontrarPorAutor(a));  
            return "administrador/librosAutor"; 
	}
        
        @GetMapping("/editarAutor")
        public String editarAutor(Model m,Integer id){
            m.addAttribute("autor", autorRepo.findById(id));
            return "administrador/editAutor";
        }
        
        @PostMapping("/guardarAutor")
	public String guardarAutor(Model m,Autor autor){
		autorRepo.save(autor);
		return "administrador/vistaAdministrador";
	}
        @GetMapping("/eliminarAutor")
	public String eliminarAutorAdmin(Integer id){
		autorRepo.deleteById(id);
		return "administrador/vistaAdministrador";
	}
}
