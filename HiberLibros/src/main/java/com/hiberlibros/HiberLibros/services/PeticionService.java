/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Peticion;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.repositories.PeticionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeticionService {
    @Autowired
    private PeticionRepository repoPeticion;
    
    public List<Peticion> consultaTodasPeticiones(){
        return repoPeticion.findAll();
    }
     
    public void insertaModificaPeticion(Peticion p){
        repoPeticion.save(p);
    }
    
    public void eliminaPeticion(Peticion p){
        repoPeticion.deleteById(p.getId());
    }
    
    public void aceptarPeticion(Peticion p){
        p.setAceptacion(Boolean.TRUE);
        p.setPendienteTratar(Boolean.FALSE);
        repoPeticion.save(p);
    }
    
    public void rechazarPeticion(Peticion p){
        p.setAceptacion(Boolean.FALSE);
        p.setPendienteTratar(Boolean.FALSE);
        repoPeticion.save(p);
    }
    
    public List<Peticion> consultarPeticionesPendientes(Usuario u){
        return repoPeticion.findByPendienteTratar(Boolean.TRUE).stream().filter(x-> x.getIdUsuarioSolicitante().equals(u.getId())).collect(Collectors.toList());
    }
    public List<Peticion> consultarPeticionesAceptadas(Usuario u){
        return repoPeticion.findByAceptacion(Boolean.TRUE).stream().filter(x-> x.getIdUsuarioSolicitante().equals(u.getId())).collect(Collectors.toList());                
    }
    public List<Peticion> consultarPeticionesRechazadas(Usuario u){
        return repoPeticion.findByAceptacion(Boolean.FALSE).stream().filter(x-> x.getIdUsuarioSolicitante().equals(u.getId())).collect(Collectors.toList());                
    }
}
