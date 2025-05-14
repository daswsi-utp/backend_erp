package com.microservice.sales.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.sales.model.quote;
import com.microservice.sales.repository.QuoteRepository;

public class QuoteController {
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	public List<quote> getQuotes(){return quoteRepository.findAll();}
	public Optional<quote> getDQuotesById(Long id) {return quoteRepository.findById(id);}
	public quote createQuote(quote quoten) {return quoteRepository.save(quoten);}
	public quote updateQuote(quote quoten) {return quoteRepository.save(quoten);}
	public void deleteQuote(Long id) {quoteRepository.deleteById(id);}
	
	
}
