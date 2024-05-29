package com.berna.curso.springboot.di.factura.models;

//import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;

@Component // Contexto singleton de la aplicación
//@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {
	@Autowired
	private Client client;
	@Value("${invoice.description}")
	private String description;
	@Autowired
	@Qualifier("default")
	private List<Item> items;
	
	@PostConstruct // Ejecuta despues de q se instancie el constructor y se hayan inyectados los componentes
	public void init() {
		System.out.println("Se inició la factura");
		client.setName(client.getName().concat(" Pepe"));
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getTotal() {
//		int total = 0;
//		
//		for (Item item : items) {
//			total += item.getSubTotal();
//		}
		int total = items.stream().map(item -> item.getSubTotal()).reduce(0, (sum, subTotal) -> sum + subTotal);
		return total;
	}
}
