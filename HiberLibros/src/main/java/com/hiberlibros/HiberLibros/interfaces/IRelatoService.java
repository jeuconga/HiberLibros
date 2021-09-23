package com.hiberlibros.HiberLibros.interfaces;

import com.hiberlibros.HiberLibros.entities.Relato;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IRelatoService {

    public List<Relato> buscarRelato(String titulo);

    public List<Relato> todos();

    public List<Relato> buscarPorValoracionMayorAMenor();

    public List<Relato> buscarPorValoracionMenorAMayor();
}
