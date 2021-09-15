/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    public Optional<Usuario> findByMailContainsIgnoreCase (String mail);
    public Optional<Usuario> findByMail (String mail);
    
}
