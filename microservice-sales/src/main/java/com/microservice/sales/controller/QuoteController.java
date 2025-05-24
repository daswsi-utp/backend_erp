package com.microservice.sales.controller;

import java.awt.Taskbar.State;
import java.util.List;

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

import com.microservice.sales.dto.QuoteRequestDTO;
import com.microservice.sales.model.quote;
import com.microservice.sales.service.QuoteService;

@RestController
@RequestMapping("/api/v1/sales/quotes")
public class QuoteController {
	
	@Autowired
	private QuoteService quoteService;
	
	@GetMapping
	public ResponseEntity<List<quote>> getQuotes(){return ResponseEntity.ok(quoteService.getQuotes());}
	
	@GetMapping("/{id}")
	public ResponseEntity<quote> getQuotesById(@PathVariable Long id){
		
		return quoteService.getQuotesById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public quote createQuote(@RequestBody quote quotesn) {return quoteService.createQuote(quotesn);} 
	
	
	
	@PutMapping
	public ResponseEntity<quote> updateQuote(@RequestBody quote quoten){return ResponseEntity.ok(quoteService.updateQuote(quoten));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQuote (@PathVariable Long id){
		
		quoteService.deleteQuote(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/approved")
	public ResponseEntity<List<quote>> getApprovedQuotes() {
	    return ResponseEntity.ok(quoteService.getQuotesByState(com.microservice.sales.model.State.APPROVED));
	}

}
