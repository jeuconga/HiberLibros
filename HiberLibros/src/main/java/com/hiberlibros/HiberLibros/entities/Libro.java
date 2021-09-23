/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.entities;

import com.mysql.cj.x.protobuf.MysqlxCursor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String isbn;
    private String titulo;
    private String idioma;
    private String uriPortada;
    private Double valoracionLibro;
    private Integer numeroValoraciones;
    
     @ManyToOne(fetch = FetchType.EAGER) 
    
    @JoinColumn(name = "id_autor")
    private Autor  autor;
     
    @ManyToOne             
    @JoinColumn(name = "id_editorial")
    private Editorial  editorial;
    
    @ManyToOne             
    @JoinColumn(name = "id_genero")
    private Genero  genero; 
    
  
}
