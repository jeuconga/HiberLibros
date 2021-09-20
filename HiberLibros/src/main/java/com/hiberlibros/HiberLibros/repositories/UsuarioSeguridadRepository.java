/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.UsuarioSeguridad;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioSeguridadRepository extends CrudRepository<UsuarioSeguridad, Integer>{

   // public Optional<UsuarioSeguridad> findByNombre(String username);
    public Optional<UsuarioSeguridad> findByIdUsuario(int idUsuario);
    public Optional<UsuarioSeguridad> findByMail(String mail);
}
