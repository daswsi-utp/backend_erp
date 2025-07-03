package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.model.quote;
import com.microservice.sales.repository.DetailQuoteRepository;
import com.microservice.sales.repository.QuoteRepository;

import jakarta.transaction.Transactional;

@Service
public class DetailQuoteService {

	@Autowired
	private DetailQuoteRepository detailQuoteRepository;
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	public List<DetailQuote> getDetailQuotes(){return detailQuoteRepository.findAll();}
	public Optional<DetailQuote> getDetailQuotesById(Long id){return detailQuoteRepository.findById(id);}
	@Transactional
    public DetailQuote createDetailQuote(DetailQuote detailQuote) {
        // Calcular valores del detalle
        double lineSubtotal = detailQuote.getAmount() * detailQuote.getPrize();
        double discount = lineSubtotal * (detailQuote.getDiscount() / 100.0);
        double tax = (lineSubtotal - discount) * (detailQuote.getTax() / 100.0);
        double lineTotal = lineSubtotal - discount + tax;
        
        detailQuote.setTotal(lineTotal);
        
        // Guardar el detalle
        DetailQuote savedDetail = detailQuoteRepository.save(detailQuote);
        
        // Recalcular la cotización completa
        recalculateQuoteTotals(savedDetail.getQuoteId().getId());
        
        return savedDetail;
    }
	
	private void recalculateQuoteTotals(Long quoteId) {
        quote quote = quoteRepository.findById(quoteId)
            .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
        
        List<DetailQuote> details = detailQuoteRepository.findByQuoteIdId(quoteId);
        
        double subtotal = 0;
        double totalDiscount = 0;
        double totalTax = 0;
        double totalAmount = 0;

        for (DetailQuote detail : details) {
            double lineSubtotal = detail.getAmount() * detail.getPrize();
            double discount = lineSubtotal * (detail.getDiscount() / 100.0);
            double tax = (lineSubtotal - discount) * (detail.getTax() / 100.0);
            double lineTotal = lineSubtotal - discount + tax;

            subtotal += lineSubtotal;
            totalDiscount += discount;
            totalTax += tax;
            totalAmount += lineTotal;
        }

        quote.setSubtotal(subtotal);
        quote.setTotalDiscount(totalDiscount);
        quote.setTotalTax(totalTax);
        quote.setTotalAmount(totalAmount);
        
        quoteRepository.save(quote);
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
