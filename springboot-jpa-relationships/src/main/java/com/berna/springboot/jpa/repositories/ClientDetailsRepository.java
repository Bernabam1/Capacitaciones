package com.berna.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.berna.springboot.jpa.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long>{

}
