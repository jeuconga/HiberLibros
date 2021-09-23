/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Rol;
import com.hiberlibros.HiberLibros.entities.UsuarioSeguridad;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Integer>{
    //public long deleteByIdUsuarioSeguridad(Integer idUsuarioSeguridad);
    public Optional<Rol> findByIdUsuario(UsuarioSeguridad us); 
}
