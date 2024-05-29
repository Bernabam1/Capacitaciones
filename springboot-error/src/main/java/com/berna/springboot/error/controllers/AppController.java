package com.berna.springboot.error.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berna.springboot.error.exceptions.UserNotFoundException;
import com.berna.springboot.error.models.domain.User;
import com.berna.springboot.error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public String index() {
		//int value = 100/0;
		int value = Integer.parseInt("10");
		System.out.println(value);
		return "OK 200";
	}
	
	@GetMapping("/show/{id}")
	public User show(@PathVariable(name = "id") Long id) {
		User user = service.findById(id);
		if(user==null)
			throw new UserNotFoundException("Error Usuario no existe");
		System.out.println(user.getName());
		return user;
	}
}
