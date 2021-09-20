/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Intercambio;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface IntercambioRepository extends JpaRepository<Intercambio, Integer>{
    public Optional<Intercambio> findByUsuarioPrestador(UsuarioLibro ul);
    public Optional<Intercambio> findByUsuarioPrestatario(UsuarioLibro ul);
}
