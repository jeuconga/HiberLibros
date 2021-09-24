package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.ForoLibro;
import com.hiberlibros.HiberLibros.interfaces.IForoLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/foros")
public class ForoLibroController {

    @Autowired
    private IForoLibroService serviceForoLibro;

    @GetMapping("/libro")
    public String recuperarForosPorLibro(Model m, Integer id) {
        m.addAttribute("foros",serviceForoLibro.recuperarForosDeLibro(id));
        return "/principal/foro";
    }
    
    @GetMapping()
    public String recuperarForos(Model m) {
        m.addAttribute("foros", serviceForoLibro.recuperarTodosLosForos());
        return "/principal/foro";
    }
    
    @GetMapping("/alta")
    public String altaForo (ForoLibro l){
        serviceForoLibro.altaForoLibro(l);
        return "/principal/altaForo";
    }
    
    
    @GetMapping("/baja")
    public String bajaForo (Integer id){
        serviceForoLibro.bajaForoLibro(id);
        return "/principal/altaForo";
    }
    
}
