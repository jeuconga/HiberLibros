package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.IRelatoService;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.interfaces.IRelatoService;
import java.util.Comparator;

@Service
public class RelatoService implements IRelatoService {

    @Autowired
    private RelatoService repoRelato;

    @Autowired
    private RelatoRepository relatoRepository;

    @Override
    public List<Relato> buscarRelato(String titulo) {
        return relatoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Relato> todos() {
        return relatoRepository.findAll();
    }
    
    @Override
    public List<Relato> buscarPorValoracionMayorAMenor() {
        List<Relato> rel = relatoRepository.findAll();
        List<Relato> relato = rel.stream().sorted(Comparator.comparingDouble(Relato::getValoracionUsuarios)).collect(Collectors.toList());

        return relato;
    }

    @Override
    public List<Relato> buscarPorValoracionMenorAMayor() {
        List<Relato> rel = relatoRepository.findAll();
        List<Relato> relato = rel.stream().sorted(Comparator.comparingDouble(Relato::getValoracionUsuarios).reversed()).collect(Collectors.toList());

        return relato;
    }
}
