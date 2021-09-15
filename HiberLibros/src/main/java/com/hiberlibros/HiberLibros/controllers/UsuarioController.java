/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Usuario;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.interfaces.UsuarioServiceI;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceI service;
    
    @GetMapping
    public String usuarioFormulario(Model m, String registro){        
        m.addAttribute("registro", registro);
        m.addAttribute("usuarios", service.usuariosList());
        return "/usuarios/usuariosFormulario";
    }
    
    @PostMapping("/guardarUsuario")
    public String usuarioRegistrar(Usuario u){
        String resultado=service.guardarUsuario(u);
        return "redirect:/usuarios?registro="+resultado;
    }
    
    @GetMapping("/borrar")
    public String borrar(Integer id){
        service.borrarUsuario(id);
        return "redirect:/usuarios";
    }
    
    
    
    
}
