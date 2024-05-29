package com.berna.microservicios.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.berna.microservicios.camelmicroserviceb.CurrencyExchange;
import com.berna.microservicios.camelmicroserviceb.MyCurrencyExchangeProcessor;
import com.berna.microservicios.camelmicroserviceb.MyCurrencyExchangeTransformer;

@Component
public class ActiveMqRecieverRouterXML extends RouteBuilder {
	@Autowired // Permite a Spring resolver y inyectar automáticamente los beans necesarios
	private MyCurrencyExchangeProcessor processor;
	@Autowired // Permite a Spring resolver y inyectar automáticamente los beans necesarios
	private MyCurrencyExchangeTransformer transformer;

	@Override
	public void configure() throws Exception {

		// XML
		// Mapear a un CurrencyExchangeBean
		// { "id": 1000, "from": "USD","to": "INR","conversionMultiple": 70 }

		from("activemq:my-activemq-xml-queue") // activemq es un componente. De aca levanto lo que fue al to en A
		.unmarshal().jacksonXml(CurrencyExchange.class)
		.bean(processor)
		.bean(transformer)
		.to("log:recieved-message-from-active-mq-XML");
	}
}