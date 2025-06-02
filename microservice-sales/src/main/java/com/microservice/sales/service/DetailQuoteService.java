package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.repository.DetailQuoteRepository;

@Service
public class DetailQuoteService {

	@Autowired
	private DetailQuoteRepository detailQuoteRepository;
	
	public List<DetailQuote> getDetailQuotes(){return detailQuoteRepository.findAll();}
	public Optional<DetailQuote> getDetailQuotesById(Long id){return detailQuoteRepository.findById(id);}
	public DetailQuote createDetailQuote(DetailQuote detailQuote) {
	    // Calcula el total automáticamente
	    double subtotal = detailQuote.getAmount() * detailQuote.getPrize();
	    double total = subtotal - detailQuote.getDiscount() + detailQuote.getTax();
	    detailQuote.setTotal(total);

	    return detailQuoteRepository.save(detailQuote);
	}
	
		

	public DetailQuote updateDetailQuote(DetailQuote detailQuote) {
	    // Calcular el total (ejemplo simple)
	    double subtotal = detailQuote.getAmount() * detailQuote.getPrize();
	    double discountAmount = subtotal * (detailQuote.getDiscount() / 100);
	    double taxAmount = (subtotal - discountAmount) * (detailQuote.getTax() / 100);
	    double total = subtotal - discountAmount + taxAmount;

	    detailQuote.setTotal(total);

	    return detailQuoteRepository.save(detailQuote);	    
	    
	}

	public void deleteDetailQuote (Long id) {detailQuoteRepository.deleteById(id);}
	public List<DetailQuote> getDetailsByQuoteId(Long quoteId) {
	    return detailQuoteRepository.findByQuoteIdId(quoteId);
	}

	
	
}
