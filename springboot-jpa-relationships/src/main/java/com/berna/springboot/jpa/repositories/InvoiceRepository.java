package com.berna.springboot.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.berna.springboot.jpa.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
