package com.hiberlibros.HiberLibros.dtos;

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
public class BuscaLibroDto {
    
    private Integer value;
    private String text;
    
}
