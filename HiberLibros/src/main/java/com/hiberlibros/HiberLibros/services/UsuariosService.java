/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Usuarios;
import com.hiberlibros.HiberLibros.interfaces.UsuariosServiceI;
import com.hiberlibros.HiberLibros.repositories.UsuariosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class UsuariosService implements UsuariosServiceI{
    @Autowired
    private UsuariosRepository urService;

    @Override
    public String guardarUsuario(Usuarios u) {
        String resultado="";
        int auxMail=u.getMail().indexOf("@");
        String mailSubstring=u.getMail().substring(auxMail);
        if(u.getNombre()==null || u.getApellido()==null || u.getDireccion()==null  || u.getCiudad()==null  || u.getMail()==null || u.getTelef()==null){
            resultado="Error: Campo requerido vacío";
        }
        else if(!mailSubstring.contains(".")){
            resultado="Error: e-mail incorrecto";
        }
        else if(urService.findByMailContainsIgnoreCase(u.getMail()).isPresent()){
            resultado="Error: Ya existe un usuario registrado con ese e-mail";
        }
        else{
            urService.save(u);
            resultado="Usuario registrado con éxito";
        }
       
        
        
        return resultado;

    }

    @Override
    public void borrarUsuario(Integer id) {
       urService.deleteById(id);
    }

    @Override
    public List<Usuarios> usuariosList() {
        return urService.findAll();
    }
    
}
