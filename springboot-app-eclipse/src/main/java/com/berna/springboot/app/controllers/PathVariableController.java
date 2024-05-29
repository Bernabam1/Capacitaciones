package com.berna.springboot.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berna.springboot.app.models.ParamDto;
import com.berna.springboot.app.models.User;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {
	@Value("${config.username}") // Traigo valores del application config
	private String username;
	@Value("${config.message}") // Esta linea también se puede pasar como argumento a un metodo
	private String message;
	@Value("${config.listOfValues}")
	private String[] listOfValues;
	@Value("${config.code}")
	private Integer code;
	@Value("#{${config.valuesMap}}") // Spring Expression Language para anidar objetos
	private Map<String, Object> valuesMap;
	@Autowired 
	private Environment environment; // Environment tiene acceso a las variables de config
	
	@GetMapping("/baz/{message}") // Variable en la ruta
	public ParamDto baz(@PathVariable String message) { // Aca va el mismo nombre q la variable en ruta
		ParamDto param = new ParamDto();
		param.setMessage(message);
		return param;
	}
	
	@GetMapping("/mix/{product}/{id}")
	public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id){
		Map<String, Object> json = new HashMap<>();
		json.put("product", product);
		json.put("id", id);
		
		return json;
	}
	
	@PostMapping("/create")
	public User create(@RequestBody User user) {
		// Popular el json con un post en postman -> body -> json
		// Acá puedo trabajar con lo que viene
		user.setName(user.getName().toUpperCase()); // ej
		return user;
	}
	
	@GetMapping("/values")
	public Map<String, Object> values(){
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("code", code);
		json.put("message", message);
		json.put("listOfValues", listOfValues);
		json.put("valueMap", valuesMap);
		
		json.put("message env", environment.getProperty("config.message")); // Puedo pedir asi el valor
		
		return json;
	}
}
