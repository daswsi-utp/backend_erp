package com.microservice.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	

}
