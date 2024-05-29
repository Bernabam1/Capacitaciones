package com.berna.microservicios.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;



@Component
public class ActiveMqRecieverRouterCSV extends RouteBuilder {

	@Override
	public void configure() throws Exception {	

		from("activemq:split-queue") // activemq es un componente. De aca levanto lo que fue al to en A
		.to("log:recieved-message-from-active-mq-CSV");
	}
}