/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IUsuarioLibroService {
    
    public UsuarioLibro encontrarId(Integer id);
    public List<UsuarioLibro> buscarContiene(String buscador);
    public List<UsuarioLibro> buscarUsuario (Usuario u);
    public List<UsuarioLibro> buscarUsuarioDisponibilidad(Usuario u, String tengo, String disponibilidad);
    public List<UsuarioLibro> todos();
    public void guardar(UsuarioLibro ul, Libro l, Usuario u);
    public void borrar(Integer id);
    public void editar(UsuarioLibro ul);
    public List<UsuarioLibro> buscarUsuariotiene(Usuario u);
    public List<UsuarioLibro> buscarDisponibles(Usuario u);
}
