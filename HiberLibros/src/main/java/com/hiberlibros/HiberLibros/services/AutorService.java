package com.hiberlibros.HiberLibros.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberlibros.HiberLibros.dtos.LibroDto;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;

@Service
public class AutorService implements IAutorService{

	@Autowired
	private AutorRepository autorRepo;
	
	@Autowired
	private AutorLibroRepository repo;
	
    @Autowired
    private ModelMapper obj;
	
	@Override
	public List<Autor> consultarAutores(String buscar){
		return autorRepo.findAll().stream()
				.filter(
						x -> x.getNombre().concat(x.getApellidos()).toLowerCase()//paso nombre completo a minusuclas para comparar
						.contains(buscar.toLowerCase())
						)
				.collect(Collectors.toList());
		
	}
	
	@Override
	public List<LibroDto> getLibros(Integer id){
        return repo.findAll()
                .stream()
                .filter(z -> z.getAutor().getIdAutor() == id)
                .map(x-> obj.map(x.getLibro(), LibroDto.class))
                .collect(Collectors.toList());
	}
}
