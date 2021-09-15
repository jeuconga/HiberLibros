package com.hiberlibros.HiberLibros.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Isabel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nombre;
    
   @OneToMany(mappedBy = "genero")
   private List<Relato> listaRelatos;
    
   @OneToMany(mappedBy = "libro")
    private List<Libro> listaLibros;
    
    
}
