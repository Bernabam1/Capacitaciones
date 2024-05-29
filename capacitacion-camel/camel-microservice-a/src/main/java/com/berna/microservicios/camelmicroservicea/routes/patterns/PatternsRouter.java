package com.berna.microservicios.camelmicroservicea.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.berna.microservicios.camelmicroservicea.CurrencyExchange;

@Component
public class PatternsRouter extends RouteBuilder {
	
	@Autowired
	private DynamicRouterBean dynamicRouterbean;

	@Override
	public void configure() throws Exception {
		// Pipeline
		// Content Based (choice())
		
		// Multicast
//		from("timer:multicast?period=10000")
//		.multicast()
//		.to("log:Log1", "log:Log2", "log:LogN");
		
//		from("file:files/csv")
//		.unmarshal().csv() 
//		.split(body()) // Separ en lineas y manda cada una como un msj
//		.to("activemq:split-queue");
		
		// Splitter
//		from("file:files/csv")
//		.convertBodyTo(String.class)
//		.split(body(),",") // Separ en lineas y manda cada una como un msj
//		.to("activemq:split-queue");
		
		// Aggregate
		from("file:files/aggregate-json")
		.unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
		.aggregate(simple("${body.to}"), new ArrayListAggregationStrategy()) // if to tiene el mismo valor, agrupo
		.completionSize(3) // Va a esperar 3 mensajes para mandar
		.to("log:aggregate-json"); // Logea un solo mensaje con los 3 json pasados por el CurrencyExchange
		
		// Slip
		//String routingSlip = "direct:endpoint1,direct:endpoint3";
		//String routingSlip = "direct:endpoint1,direct:endpoint2,direct:endpoint3";
		
//		from("timer:routingSlip?period=10000")
//		.transform().constant("My Message is Hardcoded")
//		.routingSlip(simple(routingSlip));
		
		
		// Dynamic Routing
		
		from("timer:dynamicRouting?period=10000") //period={{timePeriod}}
		.transform().constant("My Message is Hardcoded")
		.dynamicRouter(method(dynamicRouterbean));
		
		from("direct:endpoint1")
		.wireTap("log:wire-tap") // endpoint adicional
		.to("{{endpoint-for-logging}}");
		
		from("direct:endpoint2")
		.to("log:directendpoint2");
		
		from("direct:endpoint3")
		.to("log:directendpoint3");
		
		//
	}

}
