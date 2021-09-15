package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

    @GetMapping
    public String prueba(Model model) {

//        ArrayList<Genero> generos = repositorio.findAll();
//        model.addAttribute("generos",generos);
        return "/relato/relato";
    }

    @PostMapping("/guardarRelato")
    public String s(Relato relato,MultipartFile ficherosubido) {
        String subir = "c:\\zzzzSubirFicheros\\" + ficherosubido.getOriginalFilename();
        File f = new File(subir);
        f.getParentFile().mkdirs();

        try {

            Files.copy(ficherosubido.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            relato.setFichero(subir);
            repoRelato.save(relato);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/relato";
    }

    @GetMapping("/eliminarRelato")
    public String eliminarRelato(Model m, Integer id) {
        Optional<Relato> rel = repoRelato.findById(id);
        if (rel.isPresent()) {
            repoRelato.deleteById(id);
        }
        return "redirect:/relato";
    }
}
