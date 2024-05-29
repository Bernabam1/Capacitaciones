package com.berna.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.berna.springboot.di.app.springbootdi.models.Product;

@Primary //Determina que instancia concreta se va a inyectar como default
//@RequestScope Cambia el contexto, ya no sería singleton para la app sino que sería por request
//@SessionScope
@Repository // Data Access Object DAO, es un componente más específico, aporta solo semántica y facilidad de lectura al manejo de excepciones
public class ProductRepositoryImpl implements ProductRepository {
	// En repository se acceden, guardan y persisten los datos
	private List<Product> data;
	
	public ProductRepositoryImpl() {
		this.data = Arrays.asList(
				new Product(1L, "Memoria", 300L),
				new Product(2L, "CPU", 850L),
				new Product(3L, "Teclado", 180L),
				new Product(4L, "Motherboard", 490L)
				);
	}
	
	@Override
	public List<Product> findAll(){
		return data;
	}
	
	@Override
	public Product findById(Long id) {
		return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}

}