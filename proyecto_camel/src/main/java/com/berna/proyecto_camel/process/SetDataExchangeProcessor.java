package com.berna.proyecto_camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.berna.proyecto_camel.pojos.Person;

public class SetDataExchangeProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		/*
		exchange.getMessage().setBody("Body definido a partir de un process");
		exchange.getMessage().setHeader("Cabecera1", "Cabecera establecida en process");
		System.out.println("1. Body in = "+exchange.getIn().getBody());*/
		
		Person person = new Person("Lalo", 33);
		exchange.getMessage().setBody(person);
		
	}

}
