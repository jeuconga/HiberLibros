package com.hiberlibros.HiberLibros.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;

public class AutorService {

    @Autowired
    private LibroRepository libroRepo;
    
    public List<Libro> consultarLibros(Autor autor){
    	return libroRepo.findAll()
    			.stream()
    			.filter(x -> x.getAutor() == autor)
    			.collect(Collectors.toList());
    }
}
