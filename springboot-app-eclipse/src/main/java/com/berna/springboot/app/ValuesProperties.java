package com.berna.springboot.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:values.properties")
})
public class ValuesProperties {
	//Esta clase me sirve para levantar las properties sin ensuciar la clase spring ppal
}
