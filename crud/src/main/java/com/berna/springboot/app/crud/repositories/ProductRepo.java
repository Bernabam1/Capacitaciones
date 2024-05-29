package com.berna.springboot.app.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.berna.springboot.app.crud.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

}
