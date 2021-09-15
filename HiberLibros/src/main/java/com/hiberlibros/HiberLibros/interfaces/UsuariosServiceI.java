/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Usuarios;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UsuariosServiceI {
    
    public String guardarUsuario(Usuarios u);
    public void borrarUsuario(Integer id);
    public List<Usuarios> usuariosList();
    
}
