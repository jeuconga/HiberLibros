package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.repositories.GeneroRepository;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@Controller
@RequestMapping("/relato")
public class RelatoController {

    @Autowired
    private RelatoRepository repoRelato;

    @Autowired
    private GeneroRepository repoGenero;

    @GetMapping
    public String prueba(Model model) {
        List<Genero> generos = repoGenero.findAll();
        model.addAttribute("generos", generos);

        return "/relato/relato";
    }

    @PostMapping("/guardarRelato")
    public String s(Relato relato, MultipartFile ficherosubido, Model model) {
        String subir = "c:\\zzzzSubirFicheros\\" + ficherosubido.getOriginalFilename();
        File f = new File(subir);
        f.getParentFile().mkdirs();

        try {

            Files.copy(ficherosubido.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            relato.setFichero(subir);
            relato.setValoracionUsuarios(new Double(0));
            relato.setNumeroValoraciones(0);
            repoRelato.save(relato);
            model.addAttribute("correcto", "Se ha añadido correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ha ocurrido un error al insertar");

        }
        return "/relato/relato";
    }

    @GetMapping("/eliminarRelato")
    public String eliminarRelato(Model m, Integer id) {
        Optional<Relato> rel = repoRelato.findById(id);
        if (rel.isPresent()) {
            repoRelato.deleteById(id);
        }
        return "redirect:/relato";
    }

    @PostMapping("/addValoracion")
    public String addValoracion(Model m,Double valoracion,Integer id) {
        Optional<Relato> rel = repoRelato.findById(id);
        if (rel.isPresent()) {
            calcularValoracion(id,valoracion);          
          
        }
        return "/relato/relato";
    }

    public void calcularValoracion(int id, Double valoracion) {
        Optional<Relato> relato = repoRelato.findById(id);

        if (relato.isPresent()) {
            relato.get().setNumeroValoraciones(relato.get().getNumeroValoraciones() + 1);

            Double val = (relato.get().getValoracionUsuarios() * (relato.get().getNumeroValoraciones() - 1) + valoracion)
                    / relato.get().getNumeroValoraciones();

            relato.get().setValoracionUsuarios(val);
            repoRelato.save(relato.get());

        }

    }

}
