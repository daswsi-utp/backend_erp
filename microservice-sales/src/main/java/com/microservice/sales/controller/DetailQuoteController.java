package com.microservice.sales.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.model.quote;
import com.microservice.sales.repository.DetailQuoteRepository;

public class DetailQuoteController {

	@Autowired
	private DetailQuoteRepository detailQuoteRepository;
	
	public List<DetailQuote> getDetailQuotes(){return detailQuoteRepository.findAll();}
	public Optional<DetailQuote> getDetailQuotesById(Long id){return detailQuoteRepository.findById(id);}
	public DetailQuote createDetailQuote (DetailQuote detailQuote) {return detailQuoteRepository.save(detailQuote);}
	public DetailQuote updateDetailQuote (DetailQuote detailQuote) {return detailQuoteRepository.save(detailQuote);}
	public void deleteDetailQuote (Long id) {detailQuoteRepository.deleteById(id);}
	
	//public List<quote> getQuotes(){return quoteRepository.findAll();}
	//public Optional<quote> getDQuotesById(Long id) {return quoteRepository.findById(id);}
	//public quote createQuote(quote quoten) {return quoteRepository.save(quoten);}
	//public quote updateQuote(quote quoten) {return quoteRepository.save(quoten);}
	//public void deleteQuote(Long id) {quoteRepository.deleteById(id);}
	
	
}
