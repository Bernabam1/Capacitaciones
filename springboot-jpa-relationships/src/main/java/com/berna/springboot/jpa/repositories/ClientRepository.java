package com.berna.springboot.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.berna.springboot.jpa.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

	// Con el left join va a traer el cliente tenga o no facturas o direccion
	
	@Query("SELECT c FROM Client c LEFT JOIN FETCH c.addresses WHERE c.id=?1") // Va a buscar todo el cliente en una sola consulta
	Optional<Client> findOneWithAdresses(Long id);
	
	@Query("SELECT c FROM Client c LEFT JOIN FETCH c.invoices WHERE c.id=?1") // Va a buscar todo el cliente en una sola consulta
	Optional<Client> findOneWithInvoices(Long id);
	
	@Query("SELECT c FROM Client c LEFT JOIN FETCH c.invoices LEFT JOIN FETCH c.addresses WHERE c.id=?1") // Va a buscar todo el cliente en una sola consulta
	Optional<Client> findOne(Long id);
}