package com.berna.springboot.di.app.springbootdi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berna.springboot.di.app.springbootdi.models.Product;
import com.berna.springboot.di.app.springbootdi.services.ProductService;

@RestController
@RequestMapping("/api")
public class SomeController { // Singleton = Instancia compartida por todos los usuarios
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public List<Product> list(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Product show(@PathVariable Long id){
		return service.findById(id);
	}
}
