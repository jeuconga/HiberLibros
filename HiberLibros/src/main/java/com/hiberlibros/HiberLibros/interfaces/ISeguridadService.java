/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiberlibros.HiberLibros.interfaces;

/**
 *
 * @author Usuario
 */

public interface ISeguridadService {
    public String altaUsuarioSeguridad(String mail, Integer idUsuario, String password, String nombre_rol);
    public long bajaUsuarioSeguridad (Integer idUsuarioSeguridad);
    public long bajaUsuarioSeguridadPorMail (String mailUsuarioSeguridad);
    public String getMailFromContext();
}
