/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Relato;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface RelatoServiceI1 {

    public List<Relato> todos();

    public List<Relato> buscarRelato(String titulo);

    public List<Relato> buscarPorValoracionMayorAMenor();

    public List<Relato> buscarPorValoracionMenorAMayor();

}
