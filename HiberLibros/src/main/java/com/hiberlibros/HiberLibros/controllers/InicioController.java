/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.interfaces.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/hiberlibros")
public class InicioController {

    @Autowired
    private UsuarioServiceI usuService;

    @GetMapping
    public String inicio(Model m, String error) {
        m.addAttribute("error", error);
        return "principal/inicio";
    }

    @GetMapping("/panelUsuario")
    public String panelUsuario(Model m, String mail) {
        m.addAttribute("mail", mail);
        return "principal/usuarioPanel";
    }

    @PostMapping("/entrar")
    public String entrar(String mail) {
        if (usuService.registrado(mail)) {
            return "redirect:/hiberlibros/panelUsuario?mail="+mail;
        } else {
            String error="Usuario no registrado";
            return "redirect:/hiberlibros?error="+error;
        }

        
    }

}
