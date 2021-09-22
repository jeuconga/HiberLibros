/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Preferencia;
import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface PreferenciaServiceI {
    
    public List<Preferencia> getPreferencias();
    public Preferencia buscaId(Integer id);
    public List<Preferencia> buscaUsuario(Usuario usuario);
    
}
