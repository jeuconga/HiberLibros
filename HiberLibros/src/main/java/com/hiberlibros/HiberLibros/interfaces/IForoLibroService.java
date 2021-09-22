/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.ForoLibro;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IForoLibroService {
    public List<ForoLibro> recuperarForosDeLibro(Integer idLibro);
    public List<ForoLibro> recuperarTodosLosForos();
    public void altaForoLibro(ForoLibro l);
}
