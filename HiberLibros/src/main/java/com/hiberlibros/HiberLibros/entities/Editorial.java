/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import lombok.Data;


/**
 *
 * @author Usuario
 */
@Data
@Entity
@Table(name="editoriales")
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombreEditorial;

}
