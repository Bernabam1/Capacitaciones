package com.berna.curso.springboot.di.factura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berna.curso.springboot.di.factura.models.Invoice;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired // Inyecto la factura
	private Invoice invoice;
	
	@GetMapping("/show")
	public Invoice show() {
		
		//La retorno
		return invoice; 
	}
}
