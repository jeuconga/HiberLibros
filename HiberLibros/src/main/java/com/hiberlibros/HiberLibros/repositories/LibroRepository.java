/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mohamad
 */
public interface LibroRepository extends JpaRepository<Libro, Integer>{
    
}
