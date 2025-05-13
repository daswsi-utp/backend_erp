package com.microservice.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.quote;

public interface QuoteRepository extends JpaRepository<quote, Long> {

}
