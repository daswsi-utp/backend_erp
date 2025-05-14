package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.repository.DetailQuoteRepository;

public class DetailQuoteService {

	@Autowired
	private DetailQuoteRepository detailQuoteRepository;
	
	public List<DetailQuote> getDetailQuotes(){return detailQuoteRepository.findAll();}
	public Optional<DetailQuote> getDetailQuotesById(Long id){return detailQuoteRepository.findById(id);}
	public DetailQuote createDetailQuote (DetailQuote detailQuote) {return detailQuoteRepository.save(detailQuote);}
	public DetailQuote updateDetailQuote (DetailQuote detailQuote) {return detailQuoteRepository.save(detailQuote);}
	public void deleteDetailQuote (Long id) {detailQuoteRepository.deleteById(id);}
	
	
}
