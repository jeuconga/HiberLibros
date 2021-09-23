/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablaLibrosDto implements Serializable {
    Integer id;
    Integer id_libro;
    String isbn;
    String titulo;
    String autor;
    String idioma;
    String editorial;
    Double valoracion;
    String estado;
    String propietario;    
}
