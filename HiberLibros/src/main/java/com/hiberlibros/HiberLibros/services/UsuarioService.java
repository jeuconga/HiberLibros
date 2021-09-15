
package com.hiberlibros.HiberLibros.services;

import com.hiberlibros.HiberLibros.entities.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hiberlibros.HiberLibros.repositories.UsuarioRepository;
import com.hiberlibros.HiberLibros.interfaces.UsuarioServiceI;

/**
 *
 * @author Usuario
 */
@Service
public class UsuarioService implements UsuarioServiceI{
    @Autowired
    private UsuarioRepository urService;

    @Override
    public String guardarUsuario(Usuario u) {
        String resultado="";
        int auxMail=u.getMail().indexOf("@");
        String mailSubstring=u.getMail().substring(auxMail);
        if(u.getNombre()==null || u.getApellido()==null || u.getDireccion()==null  || u.getCiudad()==null  || u.getMail()==null || u.getTelef()==null){
            resultado="Error: Campo requerido vacío";
        }
        else if(!mailSubstring.contains(".")){
            resultado="Error: e-mail incorrecto";
        }
        else if(urService.findByMailContainsIgnoreCase(u.getMail()).isPresent()){
            resultado="Error: Ya existe un usuario registrado con ese e-mail";
        }
        else{
            urService.save(u);
            resultado="Usuario registrado con éxito";
        }
       
        
        
        return resultado;

    }

    @Override
    public void borrarUsuario(Integer id) {
       urService.deleteById(id);
    }

    @Override
    public List<Usuario> usuariosList() {
        return urService.findAll();
    }
    
}
