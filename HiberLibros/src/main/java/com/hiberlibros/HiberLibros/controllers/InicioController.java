/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.LibroServiceI;
import com.hiberlibros.HiberLibros.interfaces.UsuarioLibroServiceI;
import com.hiberlibros.HiberLibros.interfaces.UsuarioServiceI;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import com.hiberlibros.HiberLibros.services.EditorialService;
import com.hiberlibros.HiberLibros.services.PeticionService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    private UsuarioLibroServiceI ulService;
    @Autowired
    private PeticionService petiService;

    @Autowired
    private AuthenticationManager manager;

    @GetMapping
    public String inicio(Model m, String error) {
        if (error != null) {
            m.addAttribute("error", error);
        }

        return "/principal/login";
    }

    @PostMapping("/loginentrar")
    public String inicio(Model m, String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        List<String> roles = auth.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList());
        for (String rol : roles) {
                if ("ROLE_Administrador".equals(rol)) {
                    return "redirect:/hiberlibros/panelAdministrador?mail=" + username;
                } else {
                    if ("ROLE_Usuario".equals(rol)) {
                        return "redirect:/hiberlibros/panelUsuario?mail=" + username;
                    } 
                }
        }
        String error = "Usuario no registrado";
        return "redirect:/hiberlibros?error=" + error;
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "/principal/logout";
    }
    
    
    
    @GetMapping("/panelUsuario")
    public String panelUsuario(Model m, String mail) {
        Usuario u = usuService.usuarioRegistrado(mail);
        m.addAttribute("relatos", repoRelato.findByUsuario(u));
        m.addAttribute("usuario", u);
        m.addAttribute("libros", ulService.buscarUsuario(u));
        m.addAttribute("misPeticiones", petiService.consutarPeticionesUsuarioPendientes(u));
        m.addAttribute("petiRecibidas", petiService.consultarPeticonesRecibidas(u));
        return "principal/usuarioPanel";
    }

    @GetMapping("/guardarLibro")
    public String formularioLibro(Model m, Integer id, String buscador) {
        List<Libro> libros = new ArrayList<>();
        String noLibros = "";
        m.addAttribute("libro", new Libro());
        m.addAttribute("usuario", usuService.usuarioId(id));
        m.addAttribute("autores", autorRepo.findAll());
        m.addAttribute("autor", new Autor());
        m.addAttribute("generos", generoRepo.findAll());
        m.addAttribute("editoriales", editoService.consultaTodas());
        m.addAttribute("buscador", buscador);
        if (buscador != null) {
            libros = liService.buscarLibro(buscador);
            if (libros.size() == 0) {
                noLibros = "Ningun libro encontrado";
            } else {
                noLibros = "encontrado";
                m.addAttribute("libros", libros);
            }
        }
        m.addAttribute("noLibros", noLibros);
        return "principal/guardarLibro";
    }

    @PostMapping("/guardarLibro")
    public String guardarLibro(Integer libro, Integer usuario, UsuarioLibro ul) {
        Usuario u = usuService.usuarioId(usuario);
        Libro l = liService.libroId(libro);
        String mail = u.getMail();
        ulService.guardar(ul, l, u);
        return "redirect:/hiberlibros/panelUsuario?mail=" + mail;
    }

    @PostMapping("/formAutor")
    @ResponseBody
    public Usuario formAutor(Integer id_usuario) {
        return usuService.usuarioId(id_usuario);
    }

    @PostMapping("/saveAutor")
    public String insertarAutor(Autor autor, Integer id) {
        autorRepo.save(autor);
        return "redirect:/hiberlibros/guardarLibro?id=" + id + "&buscador=XXX";
    }

    @PostMapping("/registroLibro")
    public String registrarLibro(UsuarioLibro ul, Libro l, Integer id_usuario, Integer id_genero, Integer id_editorial, Integer id_autor) {
        l.setGenero(generoRepo.getById(id_genero));
        l.setEditorial(editoService.consultaPorIdEditorial(id_editorial));
        l.setAutor(autorRepo.findById(id_autor).get());
        liService.guardarLibro(l);
        Usuario u = usuService.usuarioId(id_usuario);
        ulService.guardar(ul, l, u);
        return "redirect:/hiberlibros/panelUsuario?mail=" + u.getMail();
    }

    @GetMapping("/buscarLibro")
    public String buscarLibro(Model m, Integer id, String buscador) {
        m.addAttribute("usuario", usuService.usuarioId(id));
        if (buscador == null) {
            m.addAttribute("libros", ulService.todos());
        } else {
            m.addAttribute("libros", ulService.buscarContiene(buscador));
        }

        return "principal/buscarLibro";
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

    @GetMapping("/borrarUL")
    public String borrarUsuLibro(Integer id, String mail) {
        ulService.borrar(id);
        return "redirect:/hiberlibros/panelUsuario?mail=" + mail;
    }

}
