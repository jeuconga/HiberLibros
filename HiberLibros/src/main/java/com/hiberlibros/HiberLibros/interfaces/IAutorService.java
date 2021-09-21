package com.hiberlibros.HiberLibros.interfaces;

import java.util.List;

import com.hiberlibros.HiberLibros.dtos.LibroDto;
import com.hiberlibros.HiberLibros.entities.Autor;

public interface IAutorService {

	public List<Autor> consultarAutores(String buscar);
	
	public List<LibroDto> getLibros(Integer id);
}
