package com.berna.springboot.error.services;

import java.util.List;

import com.berna.springboot.error.models.domain.User;

public interface UserService {
	
	List<User> findAll();
	User findById(Long id);
}
