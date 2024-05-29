package com.berna.microservicios.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;

//import org.springframework.stereotype.Component;


//@Component
public class KafkaRecieverRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("kafka:myKafkaTopic")
		.to("log:recieved-message-from-kafka");
	}
}