package com.microservice.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.DetailQuote;

public interface DetailQuoteRepository extends JpaRepository<DetailQuote, Long>{

}
