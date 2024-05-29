package com.berna.microservicios.camelmicroservicea.routes.b;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeciderBean {
	
	Logger logger = LoggerFactory.getLogger(DeciderBean.class);
	
	public boolean condition(@Body String body, @Headers Map<String,String> headers, @ExchangeProperties Map<String,String> exchange) {
		logger.info("DeciderBean body {}, Headers {}, Exchange {}", body, headers, exchange);
		return true;
	}
}