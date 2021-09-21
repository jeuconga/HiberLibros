package com.hiberlibros.HiberLibros.controllers;

import com.hiberlibros.HiberLibros.entities.Genero;
import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.UsuarioServiceI;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.interfaces.IRelatoService;
import com.hiberlibros.HiberLibros.interfaces.ISeguridadService;
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
import org.springframework.web.bind.annotation.PathVariable;
import com.hiberlibros.HiberLibros.interfaces.IUsuarioService;

@Controller
@RequestMapping("/relato")
public class RelatoController {

    @Autowired
    private RelatoRepository repoRelato;
    @Autowired
    private GeneroRepository repoGenero;
    @Autowired
    private IUsuarioService usuService;
    @Autowired
    private ISeguridadService serviceSeguridad;
    @Autowired
    private IRelatoService relatoService;

    @GetMapping
    public String prueba(Model model) {

        model.addAttribute("generos", repoGenero.findAll());
        model.addAttribute("relatos", repoRelato.findAll());

        return "/principal/relato";
    }

    @GetMapping("/listaRelatos")
    public String mostrarRelatos(Model model) {
        Usuario u = usuService.usuarioRegistrado(serviceSeguridad.getMailFromContext());
        model.addAttribute("generos", repoGenero.findAll());
        model.addAttribute("relatos", repoRelato.findAll());
        model.addAttribute("usuario", u);
        return "/principal/buscarRelatos";
    }

    @PostMapping("/guardarRelato")
    public String guardarRelato(Relato relato, MultipartFile ficherosubido
    ) {
        String subir = "c:\\zzzzSubirFicheros\\" + ficherosubido.getOriginalFilename();
        File f = new File(subir);
        f.getParentFile().mkdirs();

        try {

            Files.copy(ficherosubido.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            relato.setFichero(subir);
            relato.setValoracionUsuarios(new Double(0));
            relato.setNumeroValoraciones(0);
            repoRelato.save(relato);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:/relato";
    }

    @GetMapping("/eliminarRelato")
    public String eliminarRelato(Model m, Integer id
    ) {
        Optional<Relato> rel = repoRelato.findById(id);
        if (rel.isPresent()) {
            repoRelato.deleteById(id);
        }
        return "redirect:/relato";
    }

    @PostMapping("/addValoracion")
    public String addValoracion(Model m, Double valoracion, Integer id, Integer idUsuario) {
        try {
            Optional<Relato> rel = repoRelato.findById(id);
            if (rel.isPresent()) {
                calcularValoracion(id, valoracion);
            }
            m.addAttribute("usuario", usuService.usuarioId(idUsuario));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/relato/listaRelatos?id=" + idUsuario;
    }
    //metodo para calcular el numero de valoraciones y calcular la media entre ellas

    public void calcularValoracion(int id, Double valoracion) {
        Optional<Relato> relato = repoRelato.findById(id);
        if (relato.isPresent()) {
            relato.get().setNumeroValoraciones(relato.get().getNumeroValoraciones() + 1);
            Double val = (relato.get().getValoracionUsuarios() * (relato.get().getNumeroValoraciones() - 1) + valoracion)
                    / relato.get().getNumeroValoraciones();
            double redondeo = Math.round(val * 100.0) / 100.0;
            relato.get().setValoracionUsuarios(redondeo);
            repoRelato.save(relato.get());

        }
    }

    @GetMapping("/relato/{id}")
    public String buscarPorID(Model m, @PathVariable Integer id) {
        m.addAttribute("relato", repoRelato.findById(id));
        return "/relato/relato";
    }

    @GetMapping("/modificar")
    public String modificarRelato(Model m, Integer id) {

        m.addAttribute("relato", repoRelato.findById(id));
        m.addAttribute("generos", repoGenero.findAll());
        return "relato/modificarRelato";
    }

    @PostMapping("/modificarRelato")
    public String modificarRelato(Relato relato) {

        repoRelato.save(relato);

        return "redirect:/relato";
    }

    @GetMapping("/buscarRelato")
    public String buscarRelato(Model m, Integer id, String busqueda) {
        m.addAttribute("usuario", usuService.usuarioId(id));
        if (busqueda == null) {
            m.addAttribute("relatos", repoRelato.findAll());
        } else {
            m.addAttribute("relatos", repoRelato.findByTituloContainingIgnoreCase(busqueda));
        }

        return "/principal/buscarRelatos";
    }

    @GetMapping("/buscarPorValoracionMayor")
    public String mostrarPorValoracionMayor(Model model, Integer id) {

        model.addAttribute("generos", repoGenero.findAll());
        model.addAttribute("relatos", relatoService.buscarPorValoracionMenorAMayor());
        model.addAttribute("usuario", usuService.usuarioId(id));
        return "/principal/buscarRelatos";
    }

    @GetMapping("/buscarPorValoracionMenor")
    public String mostrarPorValoracionMenor(Model model, Integer id) {

        model.addAttribute("generos", repoGenero.findAll());
        model.addAttribute("relatos", relatoService.buscarPorValoracionMayorAMenor());
        model.addAttribute("usuario", usuService.usuarioId(id));
        return "/principal/buscarRelatos";
    }

}
