package com.berna.microservicios.camelmicroserviceb;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyCurrencyExchangeTransformer {
	
	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeTransformer.class);
	
	public CurrencyExchange processMessage(CurrencyExchange currency) {
		// Multiplico lo q hay en conversion por 10
		currency.setConversion(currency.getConversion().multiply(BigDecimal.TEN));
		
		return currency;
	}
}
