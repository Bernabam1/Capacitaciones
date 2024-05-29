package com.berna.microservicios.camelmicroservicea.router;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component esta comentado para que no salten los logs
public class MyFirstTimerRouter extends RouteBuilder { // Los componentes de ruta camel extienden de RouteBuilder

	@Autowired
	private GetCurrentTimeBean timeBean;
	
	@Autowired
	private SimpleLoggingProcessingComponent logComponent;
	
	@Override
	public void configure() throws Exception {
		// Acá se van a crear las rutas

		// Escucho algo, ej. una queue (Endpoint)
		// Puedo hacer un procesamiento (no altera el body del mensaje)
		// O hago una transformación en lo que recibo
		// Lo guardo en una DB (Endpoint)

		// para definir una ruta pongo el starting point
		from("timer:first-timer") // mensaje null
		//.log("${body}") // Hago un log del body
		//.transform().constant("My Constant Message") // Le pongo algo
		//.log("${body}") // Hago un log del body despues del transform
		.bean(timeBean, "getCurrentTime") // Paso el nombre del metodo. Si hay uno solo no hace falta
		//.log("${body}")
		.bean(logComponent)
		//.log("${body}")
		.process(new SimpleLoggingProcessor()) // Interfaz de camel para procesos
		.to("log:first-timer");
	}
}

@Component
class GetCurrentTimeBean{
	public String getCurrentTime() {
		return "Time now is " + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent{
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	public void process(String message) {
		
		logger.info("SimpleLoggingProcessingComponent {}", message);
	}
}

class SimpleLoggingProcessor implements Processor{
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("SimpleLoggingProcessingComponent {}", exchange);
		
	}
	
}
