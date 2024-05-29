package com.berna.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.berna.springboot.di.app.springbootdi.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements ProductRepository{
	
	private List<Product> list;
	
	// Necesito un constructor vacío
	public ProductRepositoryJson() {
		Resource resource = new ClassPathResource("json/product.json"); // Path del recurso
		ObjectMapper objectMapper = new ObjectMapper(); // Mapper
		try {
			list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class)); //read value lo lee como archivo y lo pobla en el objeto
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> findAll() {
		return list;
	}

	@Override
	public Product findById(Long id) {
		return list.stream().filter(p->{
			return p.getId().equals(id);
		}).findFirst().orElseThrow();
	}

}
