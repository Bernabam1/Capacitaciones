package com.berna.microservicios.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumerRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration().host("localhost").port(8000);
		
		from("timer:rest-api-consumer?period=10000")
		.setHeader("from", () -> "USD") // Funciones lambda para no hardcodear
		.setHeader("to", () -> "ARS")
		.log("${body}")
		.to("rest:get:/currency-exchange/from/{from}/to/{to}") // Aca tambien se puede hacer put o post
		.log("${body}");
	}
}
