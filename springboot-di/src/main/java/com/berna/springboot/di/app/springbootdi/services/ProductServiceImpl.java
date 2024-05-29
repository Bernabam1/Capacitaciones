package com.berna.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berna.springboot.di.app.springbootdi.models.Product;
import com.berna.springboot.di.app.springbootdi.repositories.ProductRepository;

@Service // Es un componente más específico, aporta solo semántica y facilidad de lectura al manejo de excepciones
public class ProductServiceImpl implements ProductService{// El service accede a los datos del repositorio y los trabaja
	
	//@Autowired
	private ProductRepository repository; // Iyección de la interfaz, siempre de lo más genérico
	@Autowired
	private Environment environment; // Para inyectar variables de ambiente desde el config
	// También puedo inyectar ese valor por value
	//@Value("${conig.price.tax}")
	//private Double tax;
	
	// Puedo poner un constructor para inyectar como alternativa al @Autowired
	public ProductServiceImpl(@Qualifier("productRepositoryJson") ProductRepository repository) { //El qualifier pisa la primary
		this.repository = repository;
	}
	
	@Override
	public List<Product> findAll(){
		return repository.findAll().stream().map(p -> {
			Double priceTax = p.getPrice()*environment.getProperty("config.price.tax", Double.class);
			Product newProd = (Product) p.clone(); // Esta clonada es para mantener la inmutabilidad
			newProd.setPrice(priceTax.longValue());
			return newProd;
		}).collect(Collectors.toList());
	}

	@Override
	public Product findById(Long id) {
		return repository.findById(id);
	}
}