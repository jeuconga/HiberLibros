package com.hiberlibros.HiberLibros.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;

@Service
public class AutorService {

	@Autowired
	private AutorLibroRepository repo;
	
	public List<Libro> consultarlibros(Integer id){
		return repo.findAll().stream()
				.map(x -> x.getLibro())
				.filter(x -> x.getAutor().getIdAutor() == id)
				.collect(Collectors.toList());
		
	}
}
