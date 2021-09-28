package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.EditorialRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.LibroRepository;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import com.hiberlibros.HiberLibros.repositories.UsuarioRepository;
import com.hiberlibros.HiberLibros.services.LibroService;
import com.hiberlibros.HiberLibros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mohamad
 */
@Controller
@RequestMapping("hiberlibros/paneladmin")
public class AdministradorControlller {

    @Autowired
    private LibroRepository librepo;
    @Autowired
    private GeneroRepository genRepo;
    @Autowired
    private EditorialRepository editRepo;
    @Autowired
    private AutorRepository AutRepo;
    @Autowired
    private GeneroRepository repoGenero;
    @Autowired
    private RelatoRepository repoRelato;
    @Autowired
    private UsuarioService usuService;
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
