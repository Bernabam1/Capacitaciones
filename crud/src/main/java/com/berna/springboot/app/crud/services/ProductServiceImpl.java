package com.berna.springboot.app.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.berna.springboot.app.crud.entities.Product;
import com.berna.springboot.app.crud.repositories.ProductRepo;

@Service // Acá va la lógica de negocio de acceso a datos
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;

	@Transactional(readOnly = true)
	@Override
	public List<Product> findAll() {
		return (List<Product>) repo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Product> findById(Long id) {
		return repo.findById(id);
	}

	@Transactional
	@Override
	public Product save(Product product) {
		return repo.save(product);
	}
	
	@Transactional
	@Override
	public Optional<Product> update(Long id, Product product) {
		Optional<Product> productOptional = repo.findById(id);
		if(productOptional.isPresent()) {
			// Guardo
			Product productDb = productOptional.orElseThrow();
			// Actualizo lo que viene en product del argumento
			productDb.setName(product.getName());
			productDb.setPrice(product.getPrice());
			productDb.setDescription(product.getDescription());
			// Retorno en optional
			return Optional.of(repo.save(productDb));
		}
		return productOptional;
	}

	@Transactional
	@Override
	public Optional<Product> delete(Long id) {
		Optional<Product> productOptional = repo.findById(id);
		// Valido que exista
		productOptional.ifPresent(productDb -> {
			repo.delete(productDb);
			// puedo hacer deletebyid directamente pero es void y deja registro
		});
		return productOptional; // Devuelvo para saber q se borró
		// Puedo devolver después status en el controller
	}

}
