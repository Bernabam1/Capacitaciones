package com.berna.proyecto_camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.berna.proyecto_camel.pojos.Person;

public class ProcessDataExchangeProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		/*System.out.println("2. Body in "+exchange.getMessage().getBody());
    	System.out.println("2. Header in "+exchange.getMessage().getHeader("Cabecera1"));*/
		
		System.out.println("2. Body is:");
		Person person = exchange.getMessage().getBody(Person.class); //Cuando le paso el la clase al body lo parsea
		if(person!=null) {
			System.out.println(person);
		} else {
			System.out.println("Person is null");
		}
	}

}
