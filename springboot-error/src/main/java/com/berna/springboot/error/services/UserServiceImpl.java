package com.berna.springboot.error.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.berna.springboot.error.models.domain.User;

@Service
public class UserServiceImpl implements UserService {
	private List<User> users;

	public UserServiceImpl() {
		this.users = new ArrayList<>();
		users.add(new User(1L, "Nombre1", "Apellido1"));
		users.add(new User(2L, "Nombre2", "Apellido2"));
		users.add(new User(3L, "Nombre3", "Apellido3"));
		users.add(new User(4L, "Nombre4", "Apellido4"));
		users.add(new User(5L, "Nombre5", "Apellido5"));
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User findById(Long id) {
		User user = null;
		for (User u : users) {
			if (u.getId().equals(id))
				user = u;
			break;
		}
		return user;
	}

}
