/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Relato;
import com.hiberlibros.HiberLibros.interfaces.RelatoServiceI1;
import com.hiberlibros.HiberLibros.repositories.RelatoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.interfaces.IRelatoService;

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
        List<Relato> relato = rel.stream().sorted((x, y)
                -> (int) x.getValoracionUsuarios().doubleValue() - (int) y.getValoracionUsuarios().doubleValue()).collect(Collectors.toList());

        return relato;
    }

    @Override
    public List<Relato> buscarPorValoracionMenorAMayor() {
        List<Relato> rel = relatoRepository.findAll();
        List<Relato> relato = rel.stream().sorted((x, y)
                -> (int) y.getValoracionUsuarios().doubleValue() - (int) x.getValoracionUsuarios().doubleValue()).collect(Collectors.toList());

        return relato;
    }

}
