package com.berna.microservicios.camelmicroservicea.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder{
	
	@Autowired
	private DeciderBean deciderBean;

	@Override
	public void configure() throws Exception {
		from("file:files/input") // file:directoryName file es el tipo de componente que usa camel
		.routeId("Files-Input-Route")
		.transform().body(String.class)
		.choice() // Para que pueda por ejemplo recibir diferentes tipos de archivos y hacer una logica distinta
			.when(simple("${file:ext} ends with 'xml'")) // Condicion
				.log("XML FILE")
			//.when(simple("${body} contains 'USD'"))
			.when(method(deciderBean))
				.log("NOT XML BUT CONTAINS USD")
			.otherwise()
				.log("NOT AN XML FILE")
		.end() // end of choice
		//.to("direct://log-file-varios") // Endpoint reutilizable
		.to("file:files/output");
		
		from("direct:log-file-varios") // Endpoint reutilizable
		.log("file:size")
		.log("${file:absolute.path}")
		.log("${routeId}")
		.log("${body}");
	}

}