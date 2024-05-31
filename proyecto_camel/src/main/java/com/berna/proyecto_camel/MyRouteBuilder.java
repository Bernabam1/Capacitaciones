package com.berna.proyecto_camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.berna.proyecto_camel.process.ProcessDataResponseAPIRest;
import com.berna.proyecto_camel.pojos.Product;

public class MyRouteBuilder extends RouteBuilder {
	
    private JacksonDataFormat jDataFormat;

    public MyRouteBuilder() {
        // Inicializar el data format para producto de tipo Array
        jDataFormat = new JacksonDataFormat(Product[].class);
    }

    public void configure() {
    	//Ruta a API -> GET ALL
        from("timer:simple?period=10000")
            .to("direct:consumeAPIRest")
            .end();

        from("direct:consumeAPIRest")
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            .to("https://api.restful-api.dev/objects")
            .unmarshal(jDataFormat)
            .process(new ProcessDataResponseAPIRest())
            .end();
        
        // Ruta a API con parámetro dinámico de GET por ID
        
        from("timer:anotherTimer?period=1000")
        .process(exchange->{ // Esto lo tendría q hacer en una clase aparte
        	exchange.getMessage().setHeader("id", 7); // par de clave valor, el id q le paso es el 7
        	// exchange.getMessage().setBody(7); // También se lo puedo pasar por el body
        })
        .to("direct:consumeAPIRestPorId")
        .end();
        
        from("direct:consumeAPIRestPorId")
        .setHeader(Exchange.HTTP_METHOD, constant("GET"))//Esto le dice q tipo de request se hace
        //.to("https://api.restful-api.dev/objects/7") // El to no analiza la expresión, por eso va a recipientList
        .recipientList(simple("https://api.restful-api.dev/objects/${header.id}"))//El simple es lo q hace q se analice la variable
        .log("Body por ID -> ${body}")
        .end();
    }
}
