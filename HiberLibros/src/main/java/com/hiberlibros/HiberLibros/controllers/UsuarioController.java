/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hiberlibros.HiberLibros.repositories.UsuarioRepository;

import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService serviceUsuario;
    
    @Autowired

    private UsuarioRepository usurepo;
    
//    @Autowired
//    private UsuarioSeguridad serviceUsuarioSeguridad;

    private ISeguridadService serviceUsuarioSeguridad;


    @GetMapping
    public String usuarioFormulario(Model m, String registro) { //devuelve una lista con todos los usuarios, parte administrador
        m.addAttribute("registro", registro);
        m.addAttribute("usuarios", serviceUsuario.usuariosList());
        return "/usuarios/usuariosFormulario";
    }

    @PostMapping("/guardarUsuario")//guarda un usuario devuelve un mensaje de error concreto
    public String usuarioRegistrar(Usuario u, String password) {
        //String resultado = service.guardarUsuario(u);
        String resultado = serviceUsuario.guardarUsuarioYSeguridad(u,password);
        if (resultado.contains("Error")) {
            return "redirect:/hiberlibros?error=" + resultado;//mail existente, mail no válido
        } else {

            //return "redirect:/hiberlibros/panelUsuario?mail=" + u.getMail();
            return "redirect:/hiberlibros";

        }

    }

    @PostMapping("/editarUsuario")//edita usuario, manda el usuario para rellenar el formulario
    public String usuarioEditar(Usuario u) {
        return "redirect:/hiberlibros/panelUsuario?mail=" + serviceUsuario.editarUsuario(u);
    }

    @GetMapping("/borrar")
    public String borrar(Integer id) {//borra usuario por ID en administrador
        serviceUsuario.borrarUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/borrarUsuario")//borra usuario por ID en HIBERLIBRO
    public String borrarUsuario(Integer id) {
        serviceUsuarioSeguridad.bajaUsuarioSeguridadPorMail(serviceUsuario.usuarioId(id).getMail());
        return "redirect:/hiberlibros";
    }
    
    @GetMapping("/listarAdmin")
        private String listarTodo(Model m){
            m.addAttribute("usuarios",usurepo.findAll() );
            return "/administrador/usuarios";
        }
        
        @PostMapping("/altaAdmin")
    public String altaAdmin(Usuario u, String password) {
        String resultado = serviceUsuario.guardarUsuarioYSeguridadAdmin(u,password);     
            return "/administrador/vistaAdministrador";
    }

}
