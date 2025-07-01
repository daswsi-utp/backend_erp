package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import com.microservice.sales.model.Invoice;


public interface InvoiceService {
	
	Invoice generateInvoiceFromQuote(Long quoteId);
    List<Invoice> getAllInvoices();
    Optional<Invoice> getInvoiceById(Long id);
    

}
