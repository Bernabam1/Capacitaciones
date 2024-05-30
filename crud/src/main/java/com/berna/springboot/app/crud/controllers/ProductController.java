package com.berna.springboot.app.crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.berna.springboot.app.crud.ProductValidation;
import com.berna.springboot.app.crud.entities.Product;
import com.berna.springboot.app.crud.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	//@Autowired
	//private ProductValidation validation; //La clase concreta del validator
	
	@GetMapping // Si no pongo nada va ir a la ruta por defecto
	public List<Product> list(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable Long id) { // Devuelvo un RespEntity porque puede venir un error por ej
		//Validación:
		Optional<Product> productOpt = service.findById(id);
		if (productOpt.isPresent()) {
			return ResponseEntity.ok(productOpt.orElseThrow());// Si esta presente lo devuelvo en el cuerpo de la respuesta
		}
		return ResponseEntity.notFound().build(); //Si no está, esto devuelve 404
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
		//Validacion
		//validation.validate(product, result); // Esto es la implementación de la clase validation
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		//Product productNew = service.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id){
		//Validacion
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		//Proceso
		Optional<Product> productOpt = service.update(id, product);
		if(productOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(productOpt.orElseThrow());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) { // Devuelvo un RespEntity porque puede venir un error por ej
		//Validación:
		Optional<Product> productOpt = service.delete(id);
		if (productOpt.isPresent()) {
			return ResponseEntity.ok(productOpt.orElseThrow());// Si esta presente lo devuelvo en el cuerpo de la respuesta
		}
		return ResponseEntity.notFound().build(); //Si no está, esto devuelve 404
	}
	
	// Personalización de la respuesta de error
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(err->{
			errors.put(err.getField(), "El campo "+ err.getField()+" "+ err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
}
