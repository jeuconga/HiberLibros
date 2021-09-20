/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Peticion;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;

import com.hiberlibros.HiberLibros.interfaces.IIntercambioService;
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
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioLibroService;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/hiberlibros")
public class InicioController {

    @Autowired
    private IUsuarioService usuService;
    @Autowired
    private GeneroRepository generoRepo;
    @Autowired
    private AutorRepository autorRepo;
    @Autowired
    private EditorialService editoService;
    @Autowired
    private ILibroService liService;
    @Autowired
    private RelatoRepository repoRelato;
    @Autowired
    private IUsuarioLibroService ulService;
    @Autowired
    private PeticionService petiService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private IIntercambioService serviceInter;

    @GetMapping
    public String inicio(Model m, String error) {
        if (error != null) {
            m.addAttribute("error", error);
        }

        return "/principal/login";
    }

    @Autowired
    private ISeguridadService serviceSeguridad;

    @GetMapping("/pruebaContexto")
    @ResponseBody
    public String pruebaContexto() {
        return serviceSeguridad.getMailFromContext();
    }

    @PostMapping("/loginentrar")
    public String inicio(Model m, String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        List<String> roles = auth.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList());
        for (String rol : roles) {
            if ("ROLE_Administrador".equals(rol)) {
                //return "redirect:/hiberlibros/panelAdministrador?mail=" + username;
                return "redirect:/hiberlibros/vistaAdministrador";
            } else {
                if ("ROLE_Usuario".equals(rol)) {
                    // return "redirect:/hiberlibros/panelUsuario?mail=" + username;
                    return "redirect:/hiberlibros/panelUsuario";
                }
            }
        }
  
    String error = "Usuario no registrado";
    return "redirect:/hiberlibros?error=" + error ;
}

@GetMapping("/logout")
        public String logout(){
        SecurityContextHolder.clearContext();
        return "/principal/logout";
    }
    
    
    
    @GetMapping("/panelUsuario") //entrada al panel principal de usuario, se pasan todos los elementos que se han de mostrar
        public String panelUsuario(Model m, String mail) {
        Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
            
        List<UsuarioLibro> ul = ulService.buscarUsuario(u);

        m.addAttribute("relatos", repoRelato.findByUsuario(u));
        m.addAttribute("usuario", u);
        m.addAttribute("libros", ul);
        m.addAttribute("misPeticiones", petiService.consutarPeticionesUsuarioPendientes(u));
        m.addAttribute("petiRecibidas", petiService.consultarPeticonesRecibidas(u));
        m.addAttribute("intercambiosPropios", serviceInter.encontrarULPrestador(ul));
        m.addAttribute("intercambiosPeticiones", serviceInter.encontrarULPrestatario(ul));
        return "principal/usuarioPanel";
    }


    @GetMapping("/guardarLibro") //Guarda libros en la base de datos. Primero guarda un libro, y posteriormente lo mete en la tabla Usuario Libros
        public String formularioLibro(Model m, Integer id, String buscador) {
        List<Libro> libros = new ArrayList<>();
        String noLibros = "";
        m.addAttribute("libro", new Libro());//Para el formulario
        m.addAttribute("usuario", usuService.usuarioId(id));//Pruebas pasando datos usuario, hasta hacer cookies
        m.addAttribute("autores", autorRepo.findAll());//autores para el desplegable
        m.addAttribute("autor", new Autor());//autores para formulario
        m.addAttribute("generos", generoRepo.findAll());//géneros formulario
        m.addAttribute("editoriales", editoService.consultaTodas());//editoriales formulario
        m.addAttribute("buscador", buscador);//Elemento de la barra buscador
        if (buscador != null) {//si es distinto de nulo buscara el libro por isbn o título en la base de datos
            libros = liService.buscarLibro(buscador);
            if (libros.size() == 0) {
                noLibros = "Ningun libro encontrado"; //si no existe devuelve un mensaje de error
            } else {
                noLibros = "encontrado";
                m.addAttribute("libros", libros); //si existe devuelve un arraylist con todas las coincidencias
            }
        }
        m.addAttribute("noLibros", noLibros);//devuelve el mensaje de error o de éxito
        return "principal/guardarLibro";
    }

    @PostMapping("/guardarLibro") //guarda un libro en el UsuarioLibro si ese libro existe previamente en la base de datos
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

    @PostMapping("/saveAutor")//Guarda un autor y vuelve a la página de registrar libro
        public String insertarAutor(Autor autor, Integer id) {
        autorRepo.save(autor);
        return "redirect:/hiberlibros/guardarLibro?id=" + id + "&buscador=XXX";
    }

    @PostMapping("/registroLibro")//Guarda un libro nuevo y luego lo guarda en Usuario Libro
        public String registrarLibro(UsuarioLibro ul, Libro l, Integer id_usuario, Integer id_genero, Integer id_editorial, Integer id_autor) {
        l.setGenero(generoRepo.getById(id_genero));
        l.setEditorial(editoService.consultaPorIdEditorial(id_editorial));
        l.setAutor(autorRepo.findById(id_autor).get());
        liService.guardarLibro(l);
        Usuario u = usuService.usuarioId(id_usuario);
        ulService.guardar(ul, l, u);
        return "redirect:/hiberlibros/panelUsuario?mail=" + u.getMail();//vuelve a la página inicial
    }

    @GetMapping("/buscarLibro")//Muestra la lita de libros, todos o los buscados si está relleno el campo buscador
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

    @GetMapping("/borrarUL")//borra un libro de UsuarioLibro sin eliminarlo de la tabla de Libros
        public String borrarUsuLibro(Integer id, String mail) {
        ulService.borrar(id);
        return "redirect:/hiberlibros/panelUsuario?mail=" + mail;
    }

    @GetMapping("/gestionarPeticion")
        public String gestionarPeticion(Model m, Integer id) {
        Peticion p = petiService.consultarPeticionId(id);
        m.addAttribute("peticiones", p);
        m.addAttribute("librosSolicitante", ulService.buscarUsuarioDisponibilidad(p.getIdUsuarioSolicitante(), "Tengo", "Libre"));
        return "principal/formPeticion";
    }

    @PostMapping("/realizarIntercambio")
        public String realizarIntercambio(Integer id_peticion, Integer usuarioPrestatario) {
        Peticion p = petiService.consultarPeticionId(id_peticion);
        UsuarioLibro ulPrestatario = ulService.encontrarId(usuarioPrestatario);
        UsuarioLibro ulPrestador = p.getIdUsuarioLibro();
        serviceInter.guardarIntercambio(ulPrestatario, ulPrestador);
        petiService.aceptarPeticion(p);

        return "redirect:/hiberlibros/panelUsuario?mail=" + petiService.consultarPeticionId(id_peticion).getIdUsuarioLibro().getUsuario().getMail();
    }

    @GetMapping("/finIntercambio")
        public String finIntercambio(Integer id, Integer id_usuario) {
        serviceInter.finIntercambio(id);
        return "redirect:/hiberlibros/panelUsuario?mail=" +usuService.usuarioId(id_usuario).getMail();
    }

}
