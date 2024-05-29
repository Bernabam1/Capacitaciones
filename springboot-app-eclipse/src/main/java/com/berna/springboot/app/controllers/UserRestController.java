package com.berna.springboot.app.controllers;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.berna.springboot.app.models.User;
import com.berna.springboot.app.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@GetMapping("/details-dto")
	public UserDto details(){
		User user = new User("Bernardo", "Michelli");
		UserDto userDto = new UserDto();
		
		userDto.setUser(user);
		userDto.setTitle("Usuario DTO");
		
		return userDto;
	}
	
	@GetMapping("/details-map")
	public Map<String, Object> detailsMap(){
		User user = new User("Bernardo", "Michelli");
		Map<String, Object> body = new HashMap<>();
		
		body.put("title", "Hola Mundo Spring Boot");
		body.put("user", user);
		
		return body;
	}
	
	@GetMapping("/list")
	public List<User> userList(){
		User user = new User("Bernardo", "Michelli");
		User user2 = new User("Ramona", "Michelli");
		User user3 = new User("Brother", "Sin apellido");
		
		List<User> users = Arrays.asList(user, user2, user3); // Helper
//		List<User> users = new ArrayList<>();
//		
//		users.add(user);
//		users.add(user2);
//		users.add(user3);
		
		return users;
	}
	
}
