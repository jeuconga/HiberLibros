/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import com.hiberlibros.HiberLibros.repositories.UsuarioLibroRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioLibroService;

/**
 *
 * @author Usuario
 */
@Service
public class UsuarioLibroService implements IUsuarioLibroService {
    @Autowired
    private UsuarioLibroRepository ulRepo;
    @Autowired
    private LibroService libService;

    @Override
    public UsuarioLibro encontrarId(Integer id) {
        return ulRepo.findById(id).get();
    }

    @Override
    public List<UsuarioLibro> buscarContiene(String buscador) {
        List<UsuarioLibro> ul=new ArrayList<>();
        List<Libro> l=libService.buscarLibro(buscador); //busca libros que contentan ese parámetro       
        l.forEach(x->{
            List<UsuarioLibro> ulAux=ulRepo.findByLibro(x);//Encuentra los libros que coiniciden dentro de usuarioLibros
            ulAux.forEach(y->{
                ul.add(y);
            });
        });
        
        return ul;
    }

    @Override
    public List<UsuarioLibro> buscarUsuario(Usuario u) {//busca por usuario
        return ulRepo.findByUsuario(u);
    }

    @Override
    public List<UsuarioLibro> todos() {
       return ulRepo.findAll();
    }

    @Override
    public void guardar(UsuarioLibro ul, Libro l, Usuario u) {//guarda el registro
        ul.setLibro(l);
        ul.setUsuario(u);
        ulRepo.save(ul);
        
    }

    @Override
    public void borrar(Integer id) {
        
        ulRepo.deleteById(id);
    }

    @Override
    public List<UsuarioLibro> buscarUsuarioDisponibilidad(Usuario u, String tengo, String disponibilidad) {
        return ulRepo.findByUsuarioAndQuieroTengoAndEstadoPrestamo(u, tengo, disponibilidad);
    }
    
    
    
}
