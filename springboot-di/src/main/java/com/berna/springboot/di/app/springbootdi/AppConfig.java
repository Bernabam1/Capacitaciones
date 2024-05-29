package com.berna.springboot.di.app.springbootdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.berna.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.berna.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {
	// En esta clase estoy seteando el path de config.properties como @Configuration
	
	@Bean
	ProductRepository productRepositoryJson() { // el public no hace falta
		return new ProductRepositoryJson();
		//Forma alternativa de crear un componente Spring
	}
}
