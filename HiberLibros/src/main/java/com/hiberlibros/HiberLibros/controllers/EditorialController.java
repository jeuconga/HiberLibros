package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Editorial;
import com.hiberlibros.HiberLibros.services.EditorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService serviceEditorial;

    @RequestMapping(value = "/editoriales", method = {RequestMethod.POST, RequestMethod.GET})
    public String editoriales(Model m, Editorial editorial) {
        if (editorial.getId() == null) {
            editorial = new Editorial();
        } else {
            editorial = serviceEditorial.consultaPorIdEditorial(editorial.getId());
        }
        m.addAttribute("editorial", editorial);
        m.addAttribute("editoriales", serviceEditorial.consultaTodas());
        return "/editoriales/editoriales";
    }

    @PostMapping("alta")
    public String editorialesAlta(Model m, Editorial ed) {
        List<Editorial> editoriales = serviceEditorial.consultaPorNombreEditorial(ed);
        String errMensaje = null;
        if (editoriales.size() > 0) {
            errMensaje = "editorial existente";
        } else {
            serviceEditorial.altaModificacionEditorial(ed);
            m.addAttribute("editorial", serviceEditorial.consultaPorIdEditorial(ed.getId()));
        }
        m.addAttribute("errMensaje", errMensaje);
        return "redirect:/editoriales/editoriales";
    }

    @PostMapping("baja")
    public String editorialesBaja(Model m, int id) {
        serviceEditorial.bajaEditorial(id);
        return "redirect:/editoriales/editoriales";
    }

    @PostMapping("modificacion")
    public String editorialesModificacion(Model m, Editorial ed) {
        serviceEditorial.altaModificacionEditorial(ed);
        return "redirect:/editoriales/editoriales";
    }

    @PostMapping("consulta")
    public String editorialesConsulta(Model m, String id) {
        m.addAttribute("editorial", serviceEditorial.consultaPorIdEditorial(Integer.parseInt(id)));

        return "forward:/editoriales/editoriales";
    }
}