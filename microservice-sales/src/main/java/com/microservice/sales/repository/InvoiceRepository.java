package com.microservice.sales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	Optional<Invoice> findBySaleId(Long saleId);


}
