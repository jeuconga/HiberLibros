package com.hiberlibros.HiberLibros.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class InicioController {

	@GetMapping("/inicio")
	public String inicio(){
		return "principal/paginaprincipal";
	}
	@GetMapping("/login")
	public String login(){
		return "principal/login";
	}
	@GetMapping("/registro")
	public String registro(){
		return "principal/registro";
	}
}
