package com.berna.proyecto_camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.berna.proyecto_camel.pojos.Product;

public class ProcessDataResponseAPIRest implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Product[] productsArray = exchange.getIn().getBody(Product[].class);
		if (productsArray != null && productsArray.length > 0) {
			// Process the array of products
			for (Product product : productsArray) {
				// Do something with each product
				System.out.println("Product Name: " + product.getName());
			}
		} else {
			System.out.println("Products array is empty or null");
		}
	}
}
	