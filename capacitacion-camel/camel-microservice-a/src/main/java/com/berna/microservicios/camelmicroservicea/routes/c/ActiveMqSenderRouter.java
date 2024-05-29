package com.berna.microservicios.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;

//@Component
public class ActiveMqSenderRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// Timer
//		from("timer:active-mq-timer?period=10000") // Cada 10 segundos se ejecuta el timer
//		.transform().constant("My message") // Manda un mensaje a la cola
//		.log("${body}")
//		.to("activemq:my-activemq-queue");
		// Queue
		
		from("file:files/json")
		.log("${body}")
		.to("activemq:my-activemq-queue");
	}

	
}
