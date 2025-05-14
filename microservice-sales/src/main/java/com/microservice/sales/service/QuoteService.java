package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.sales.model.quote;
import com.microservice.sales.repository.QuoteRepository;

@Service
public class QuoteService {
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	public List<quote> getQuotes(){return quoteRepository.findAll();}
	public Optional<quote> getQuotesById(Long id) {return quoteRepository.findById(id);}
	public quote createQuote(quote quoten) {return quoteRepository.save(quoten);}
	public quote updateQuote(quote quoten) {return quoteRepository.save(quoten);}
	public void deleteQuote(Long id) {quoteRepository.deleteById(id);}
	
	
}
