package com.microservice.sales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.quote;

public interface QuoteRepository extends JpaRepository<quote, Long> {
	
	List<quote> findByState(com.microservice.sales.model.State state);

}
