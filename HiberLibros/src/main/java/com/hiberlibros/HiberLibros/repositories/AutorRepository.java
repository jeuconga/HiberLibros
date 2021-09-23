package com.hiberlibros.HiberLibros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberlibros.HiberLibros.entities.Autor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

}
