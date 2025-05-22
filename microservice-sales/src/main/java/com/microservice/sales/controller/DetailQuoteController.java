package com.microservice.sales.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.sales.model.DetailQuote;
import com.microservice.sales.service.DetailQuoteService;

@RestController
@RequestMapping("/api/v1/sales/detailquote")
public class DetailQuoteController {
	
	@Autowired 
	private DetailQuoteService detailQuoteService;
	
	@GetMapping
	public ResponseEntity<List<DetailQuote>> getDetailQuotes(){return ResponseEntity.ok(detailQuoteService.getDetailQuotes());}
	
	@GetMapping("/{id}")
    public ResponseEntity<DetailQuote> getDetailQuoteById(@PathVariable Long id) {
        Optional<DetailQuote> detail = detailQuoteService.getDetailQuotesById(id);
        return detail.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    } 
	
	
	 @PostMapping
	    public ResponseEntity<DetailQuote> createDetailQuote(@RequestBody DetailQuote detailQuote) {
	        DetailQuote created = detailQuoteService.createDetailQuote(detailQuote);
	        return ResponseEntity.ok(created);
	    }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<DetailQuote> updateDetailQuote(@PathVariable Long id, @RequestBody DetailQuote detailQuote) {
	        Optional<DetailQuote> existing = detailQuoteService.getDetailQuotesById(id);
	        if (existing.isPresent()) {
	            detailQuote.setId(id); // Asegura que se actualiza el correcto
	            DetailQuote updated = detailQuoteService.updateDetailQuote(detailQuote);
	            return ResponseEntity.ok(updated);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQuote (@PathVariable Long id){
		
		detailQuoteService.deleteDetailQuote(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/by-quote/{quoteId}")
	public ResponseEntity<List<DetailQuote>> getDetailsByQuoteId(@PathVariable Long quoteId) {
	    List<DetailQuote> details = detailQuoteService.getDetailsByQuoteId(quoteId);
	    return ResponseEntity.ok(details);
	}

	

}
