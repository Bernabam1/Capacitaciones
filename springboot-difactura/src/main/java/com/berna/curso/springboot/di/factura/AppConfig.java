package com.berna.curso.springboot.di.factura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.berna.curso.springboot.di.factura.models.Item;
import com.berna.curso.springboot.di.factura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

	@Bean("default")
	List<Item> itemsInvoice() {
		Product p1 = new Product("Camara", 800);
		Product p2 = new Product("Bicicleta", 1200);
		Product p3 = new Product("Silla", 100);

		List<Item> items = Arrays.asList(new Item(p1, 2), new Item(p2, 4), new Item(p3, 1));
		return items;
	}

	@Bean
	List<Item> itemsInvoice2() {
		Product p1 = new Product("Perro", 800);
		Product p2 = new Product("Gato", 1200);
		Product p3 = new Product("Termo", 100);
		Product p4 = new Product("Muñeco", 500);

		List<Item> items = Arrays.asList(new Item(p1, 1), new Item(p2, 1), new Item(p3, 6), new Item(p4, 2));
		return items;
	}
}
