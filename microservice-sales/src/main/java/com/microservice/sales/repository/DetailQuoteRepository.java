package com.microservice.sales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.model.quote;

public interface DetailQuoteRepository extends JpaRepository<DetailQuote, Long>{
	List<DetailQuote> findByQuoteIdId(Long quoteId);

	 void deleteByQuoteId(quote quote);

}
