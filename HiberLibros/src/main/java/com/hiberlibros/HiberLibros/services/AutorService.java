package com.hiberlibros.HiberLibros.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorLibroRepository repo;
	@Autowired
	private AutorRepository autorRepo;
	
	public List<Libro> consultarlibros(Integer id){
		return repo.findAll().stream()
				.map(x -> x.getLibro())
				.filter(x -> x.getAutor().getIdAutor() == id)
				.collect(Collectors.toList());
		
	}
	public List<Autor> consultarAutores(String buscar){
		return autorRepo.findAll().stream()
				.filter(
						x -> x.getNombre().concat(x.getApellidos()).toLowerCase()//paso nombre completo a minusuclas para comparar
						.contains(buscar.toLowerCase())
						)
				.collect(Collectors.toList());
		
	}
}
