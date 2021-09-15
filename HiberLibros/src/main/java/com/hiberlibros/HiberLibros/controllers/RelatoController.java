/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/relato")
public class RelatoController {

    @Autowired
    private RelatoRepository repoRelato;

    @GetMapping
    public String prueba() {
        return "/relato/relato";
    }

    @PostMapping("/guardarRelato")
    public String s(Relato relato) {
//        String subir = "c:\\zzzzSubirFicheros\\" + fichero.getOriginalFilename() + ".txt";
//        File f = new File(subir);
//        f.getParentFile().mkdirs();

        try {

//            Files.copy(fichero.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
//           
//            relato.setFichero(subir);
            repoRelato.save(relato);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/relato";
    }
}
