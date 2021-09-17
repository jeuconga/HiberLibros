/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.LibroServiceI;
import com.hiberlibros.HiberLibros.interfaces.UsuarioServiceI;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import com.hiberlibros.HiberLibros.services.EditorialService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/hiberlibros")
public class InicioController {

    @Autowired
    private UsuarioServiceI usuService;
    @Autowired
    private GeneroRepository generoRepo;
    @Autowired
    private AutorRepository autorRepo;
    @Autowired
    private EditorialService editoService;
    @Autowired
    private LibroServiceI liService;
    @Autowired
    private RelatoRepository repoRelato;

    @GetMapping
    public String inicio(Model m, String error) {
        if (error != null) {
            m.addAttribute("error", error);
        }

        return "/principal/login";
    }

    @GetMapping("/panelUsuario")
    public String panelUsuario(Model m, String mail) {
        m.addAttribute("usuario", usuService.usuarioRegistrado(mail));
        
        //mostrar relatos por ID logeado
        m.addAttribute("relatos", repoRelato.findByUsuario(usuService.usuarioRegistrado(mail)));
        return "principal/usuarioPanel";
    }

    @PostMapping("/entrar")
    public String entrar(String mail) {
        if (usuService.registrado(mail)) {
            return "redirect:/hiberlibros/panelUsuario?mail=" + mail;
        } else {
            String error = "Usuario no registrado";
            return "redirect:/hiberlibros?error=" + error;
        }
    }

    @GetMapping("/guardarLibro")
    public String formularioLibro(Model m, Integer id, String buscador) {
        List<Libro> libros = new ArrayList<>();
        String noLibros = "";
        m.addAttribute("libro", new Libro());
        m.addAttribute("usuario", usuService.usuarioId(id));
        m.addAttribute("autores", autorRepo.findAll());
        m.addAttribute("generos", generoRepo.findAll());
        m.addAttribute("editoriales", editoService.consultaTodas());
        if (buscador != null) {
            libros = liService.buscarLibro(buscador);
            if (libros == null) {
                noLibros = "Ningun libro encontrado";
            } else {
                m.addAttribute("libros", libros);
            }
        }
        m.addAttribute("noLibros", noLibros);

        return "principal/guardarLibro";
    }

    @PostMapping("/guardarRelato")
    public String formularioRelato(Model m, Integer id, Relato relato, MultipartFile ficherosubido) {
        String subir = "c:\\zzzzSubirFicheros\\" + ficherosubido.getOriginalFilename();
        File f = new File(subir);
        f.getParentFile().mkdirs();
        try {
            Files.copy(ficherosubido.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            relato.setFichero(subir);
            relato.setValoracionUsuarios(new Double(0));
            relato.setNumeroValoraciones(0);
            relato.setUsuario(usuService.usuarioId(id));
            repoRelato.save(relato);
            m.addAttribute("usuario", usuService.usuarioId(id));
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "redirect:/hiberlibros/panelUsuario?mail=" + usuService.usuarioId(id).getMail();
    }

    @GetMapping("/relato")
    public String prueba(Model model, Integer id) {

        model.addAttribute("generos", generoRepo.findAll());
        model.addAttribute("relatos", repoRelato.findAll());
        model.addAttribute("usuario", usuService.usuarioId(id));
        return "principal/relato";
    }

}
