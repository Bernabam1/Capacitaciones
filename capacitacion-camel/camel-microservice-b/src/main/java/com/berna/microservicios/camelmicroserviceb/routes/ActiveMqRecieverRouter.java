package com.berna.microservicios.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.berna.microservicios.camelmicroserviceb.CurrencyExchange;
import com.berna.microservicios.camelmicroserviceb.MyCurrencyExchangeProcessor;
import com.berna.microservicios.camelmicroserviceb.MyCurrencyExchangeTransformer;

@Component
public class ActiveMqRecieverRouter extends RouteBuilder {
	@Autowired // Permite a Spring resolver y inyectar automáticamente los beans necesarios
	private MyCurrencyExchangeProcessor processor;
	@Autowired // Permite a Spring resolver y inyectar automáticamente los beans necesarios
	private MyCurrencyExchangeTransformer transformer;

	@Override
	public void configure() throws Exception {

		// Json
		// Mapear a un CurrencyExchangeBean
		// { "id": 1000, "from": "USD","to": "INR","conversionMultiple": 70 }

		from("activemq:my-activemq-queue") // activemq es un componente. De aca levanto lo que fue al to en A
			.unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class) // Importar dependencia camel-jackson-starter
			//unmarshal Usa ObjectMapper para convertir el JSON en una instancia de CurrencyExchange.
			.bean(processor)
			.bean(transformer) // Este hace una transformación (multiplica por 10 la conversion)
			.to("log:recieved-message-from-active-mq");
	}
}