/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Usuarios;
import com.hiberlibros.HiberLibros.interfaces.UsuariosServiceI;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuariosServiceI service;
    
    @GetMapping
    public String usuarioFormulario(Model m, String registro){        
        m.addAttribute("registro", registro);
        m.addAttribute("usuarios", service.usuariosList());
        return "/usuarios/usuariosFormulario";
    }
    
    @PostMapping("/guardarUsuario")
    public String usuarioRegistrar(Usuarios u){
        String resultado=service.guardarUsuario(u);
        return "redirect:/usuarios?registro="+resultado;
    }
    
    @GetMapping("/borrar")
    public String borrar(Integer id){
        service.borrarUsuario(id);
        return "redirect:/usuarios";
    }
    
    
    
    
}
