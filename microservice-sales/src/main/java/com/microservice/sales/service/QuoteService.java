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
	public quote createQuote(quote quoten) {
	    double subtotal = 0;
	    double totalDiscount = 0;
	    double totalTax = 0;

	    if (quoten.getDetails() != null) {
	        for (var detail : quoten.getDetails()) {
	            detail.setQuoteId(quoten);

	            double lineSubtotal = detail.getAmount() * detail.getPrize();
	            double discount = detail.getDiscount(); // Considera que puede ser fijo o porcentaje
	            double tax = detail.getTax();

	            // Si son porcentajes:
	            // discount = lineSubtotal * (discount / 100.0);
	            // tax = (lineSubtotal - discount) * (tax / 100.0);

	            double lineTotal = lineSubtotal - discount + tax;
	            detail.setTotal((int) lineTotal); // o mejor, guarda como Double

	            subtotal += lineSubtotal;
	            totalDiscount += discount;
	            totalTax += tax;
	        }
	    }

	    quoten.setSubtotal(subtotal);
	    quoten.setTotalDiscount(totalDiscount);
	    quoten.setTotalTax(totalTax);
	    quoten.setTotalAmount(subtotal - totalDiscount + totalTax);

	    return quoteRepository.save(quoten);
	}



	public quote updateQuote(quote quoten) {return quoteRepository.save(quoten);}
	public void deleteQuote(Long id) {quoteRepository.deleteById(id);}
	
	
}
