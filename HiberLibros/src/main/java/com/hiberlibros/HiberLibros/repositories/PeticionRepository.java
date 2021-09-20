/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.repositories;

import com.hiberlibros.HiberLibros.entities.Peticion;
import com.hiberlibros.HiberLibros.entities.Usuario;
import com.hiberlibros.HiberLibros.entities.UsuarioLibro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeticionRepository extends JpaRepository<Peticion, Integer>{
    public List<Peticion> findByPendienteTratar(Boolean b);
    public List<Peticion> findByAceptacion(Boolean p);
    public List<Peticion> findByPendienteTratarAndIdUsuarioSolicitante(Boolean b, Usuario u);
    public List<Peticion> findByIdUsuarioLibroAndPendienteTratar(UsuarioLibro ul,Boolean b);
}
