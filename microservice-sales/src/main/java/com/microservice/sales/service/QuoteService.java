package com.microservice.sales.service;

import java.awt.Taskbar.State;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.sales.dto.DetailQuoteDTO;
import com.microservice.sales.dto.QuoteRequestDTO;
import com.microservice.sales.model.DetailQuote;
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
		double totalAmount = 0;

		if (quoten.getDetails() != null) {
		for (var detail : quoten.getDetails()) {
			detail.setQuoteId(quoten);

				//Calcular subtotal de la línea (cantidad * precio unitario)
				double lineSubtotal = detail.getAmount() * detail.getPrize();
	           
				//Calcular descuento (como porcentaje del subtotal de la línea)
				double discount = lineSubtotal * (detail.getDiscount() / 100.0); // Considera que puede ser fijo o porcentaje
	            
	            //Calcular impuesto (sobre el subtotal MENOS el descuento)
	            double tax = (lineSubtotal - discount) * (detail.getTax() / 100.0);

	            //Calcular total de la línea
	            double lineTotal = lineSubtotal - discount + tax;
	            detail.setTotal(lineTotal);

	            subtotal += lineSubtotal;
	            totalDiscount += discount;
	            totalTax += tax;
	            totalAmount += lineTotal;
	        }
	    }

		quoten.setSubtotal(subtotal);
	    quoten.setTotalDiscount(totalDiscount);
	    quoten.setTotalTax(totalTax);
	    quoten.setTotalAmount(totalAmount); // Este es el total final con descuentos e impuestos

	    return quoteRepository.save(quoten);
	}
	
	
	
	
	public quote updateQuote(Long id, quote quoteUpdate) {
	    return quoteRepository.findById(id).map(existingQuote -> {
	        // Verifica que el ID del objeto a actualizar coincida con el ID proporcionado
	        if (!existingQuote.getId().equals(quoteUpdate.getId())) {
	            throw new IllegalArgumentException("El ID de la cotización no coincide.");
	        }

	        // Actualizar solo campos permitidos
	        existingQuote.setExpirationDate(quoteUpdate.getExpirationDate());
	        existingQuote.setState(quoteUpdate.getState());
	        existingQuote.setTypePayment(quoteUpdate.getTypePayment());
	        existingQuote.setObservation(quoteUpdate.getObservation());

	        // No tocar: details, subtotal, totalDiscount, totalTax, totalAmount
	        return quoteRepository.save(existingQuote);	
	    }).orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
	}
	

	
	public void deleteQuote(Long id) {quoteRepository.deleteById(id);}	
	
	public List<quote> getQuotesByState(com.microservice.sales.model.State state) {
	    return quoteRepository.findByState(state);
	}


	
}
