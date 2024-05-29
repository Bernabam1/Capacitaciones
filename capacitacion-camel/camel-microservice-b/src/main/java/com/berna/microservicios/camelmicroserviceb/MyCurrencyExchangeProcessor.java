package com.berna.microservicios.camelmicroserviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCurrencyExchangeProcessor {
	
	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);
	
	public void processMessage(CurrencyExchange currency) {
		logger.info("Este proceso muestra el valor q viene de un getConversion: {}", currency.getConversion());
	}
}
