/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UsuarioServiceI {
    
    public String guardarUsuario(Usuario u);
    public String editarUsuario(Usuario u);
    public void borrarUsuario(Integer id);
    public List<Usuario> usuariosList();
    public boolean registrado(String mail);
    public Usuario usuarioRegistrado(String mail);
    
}
