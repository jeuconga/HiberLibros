/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    private String apellido;
    private String direccion;
    private String ciudad;
    private String mail;
    private String telef;
    private Double valoracionUsuario;
    private Double longitud;
    private Double latitud;
    private String uriFoto;
    
    /*@OneToMany(mappedBy = "usuarioLibro",fetch=FetchType.EAGER)
    private List<UsuarioLibro> usuarioLibro;
    
    @OneToMany(mappedBy = "usuario",fetch=FetchType.EAGER)
    private List<Relato> relato;
    
    @OneToMany(mappedBy = "usuarioPreferencia",fetch=FetchType.EAGER)
    private List<Preferencia> preferencia;*/
    
    
    
    
}
