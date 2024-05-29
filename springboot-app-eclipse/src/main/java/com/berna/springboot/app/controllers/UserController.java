package com.berna.springboot.app.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.berna.springboot.app.models.User;

@Controller
public class UserController {
	
	@GetMapping("/details")
	public String details(Map<String, Object> map) {
		User user = new User("Bernardo", "Michelli");
		map.put("title", "Hola Mundo Spring Boot");
		map.put("user", user);
		
		return "details";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model) {
//		List<User> users = Arrays.asList(new User("Nombre1", "Apellido1"), 
//				new User("Nombre2", "Apellido2", "Correo@"),
//				new User("Nombre3", "Apellido3"),
//				new User("Nombre4", "Apellido4"));
//		
//		model.addAttribute("users", users);
		model.addAttribute("title", "Listado de usuarios");
		
		return "list";
	}
	
	@ModelAttribute("users") //Lo que devuelve se guarda en el atributo users de todos los metodos
	public List<User> userModels(){
		List<User> users = Arrays.asList(new User("Nombre1", "Apellido1"), 
				new User("Nombre2", "Apellido2", "Correo@"),
				new User("Nombre3", "Apellido3"),
				new User("Nombre4", "Apellido4"));
		return users;
	}
}
