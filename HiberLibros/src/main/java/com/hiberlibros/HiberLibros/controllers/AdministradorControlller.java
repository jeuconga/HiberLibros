package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Mohamad
 */
@Controller
@RequestMapping("hiberlibros/paneladmin")
public class AdministradorControlller {


    @Autowired
    private IUsuarioService usuService;
    @Autowired
    private ILibroService libserv;

    @GetMapping
    public String adminHub(Model m) {
           m.addAttribute("numUsuarios",usuService.contarUsuarios());
           m.addAttribute("numLibros",libserv.contarLibros());
        return "administrador/adminPanel";
    }
    @GetMapping("/contacto")
    public String adminContacto(Model m) {
        return "administrador/contacto";
    }
}
