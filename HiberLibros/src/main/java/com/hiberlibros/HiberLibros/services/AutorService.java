package com.hiberlibros.HiberLibros.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiberlibros.HiberLibros.dtos.LibroDto;
import com.hiberlibros.HiberLibros.entities.Autor;
import com.hiberlibros.HiberLibros.entities.Libro;
import com.hiberlibros.HiberLibros.interfaces.IAutorService;
import com.hiberlibros.HiberLibros.interfaces.ILibroService;
import com.hiberlibros.HiberLibros.repositories.AutorLibroRepository;
import com.hiberlibros.HiberLibros.repositories.AutorRepository;
import java.util.ArrayList;

@Service
public class AutorService implements IAutorService {

    @Autowired
    private AutorRepository autorRepo;

    @Autowired
    private AutorLibroRepository repo;
    
    @Autowired
    private ILibroService serviceLib;

    @Autowired
    private ModelMapper obj;

    @Override
    public List<Autor> buscarAutores(String buscar) {
        return autorRepo.findByNombreContainsIgnoreCaseAndApellidosContainsIgnoreCase(buscar, buscar);
//        return autorRepo.findAll().stream()
//                .filter(
//                        x -> x.getNombre().concat(x.getApellidos()).toLowerCase()//paso nombre completo a minusuclas para comparar
//                                .contains(buscar.toLowerCase())
//                )
//                .collect(Collectors.toList());
    }

    @Override
    public List<LibroDto> getLibros(Integer id) {
        return repo.findAll()
                .stream()
                .filter(z -> z.getAutor().getIdAutor() == id)
                .map(x -> obj.map(x.getLibro(), LibroDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void guardarAutor(Autor a) {
        autorRepo.save(a);
    }

    @Override
    public Optional<Autor> encontrarAutor(Integer id) {
        return autorRepo.findByIdAutorAndDesactivado(id, Boolean.FALSE);
    }

    @Override
    public void borrarAutor(Integer id) {
        Optional<Autor> a=encontrarAutor(id);
        List<Libro> l=new ArrayList<>();
        if(a.isPresent()){
            l=serviceLib.encontrarPorAutor(a.get());
             if(l.size()==0 || l==null){
                 autorRepo.deleteById(id);
             }else{
                 a.get().setDesactivado(Boolean.TRUE);
                 autorRepo.save(a.get());
                 l.forEach(x->{
                     serviceLib.bajaLibroId(x.getId());
                 });
             }
        }

        

    }

    @Override
    public List<Autor> consultarAutores() {
        return autorRepo.findByDesactivado(Boolean.FALSE);
    }
}
