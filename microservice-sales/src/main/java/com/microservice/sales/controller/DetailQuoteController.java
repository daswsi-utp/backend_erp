package com.microservice.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//@GetMapping
	//public ResponseEntity<List<quote>> getQuotes(){return ResponseEntity.ok(quoteService.getQuotes());}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteQuote (@PathVariable Long id){
		
		detailQuoteService.deleteDetailQuote(id);
		return ResponseEntity.noContent().build();
		
	}
	

}
