/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Usuarios;
import com.hiberlibros.HiberLibros.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuariosRepository ruService;
    
    @GetMapping
    public String usuarioFormulario(){
        return "/usuarios/usuariosFormulario";
    }
    
    @PostMapping("/guardarUsuario")
    public String usuarioRegistrar(Usuarios u){
        
        ruService.save(u);
        return "redirect:/usuarios";
    }
    
    
    
    
}
