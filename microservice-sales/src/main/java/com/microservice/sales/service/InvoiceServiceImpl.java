// InvoiceServiceImpl.java
package com.microservice.sales.service;

import com.microservice.sales.factory.InvoiceFactory;
import com.microservice.sales.model.*;
import com.microservice.sales.repository.InvoiceRepository;
import com.microservice.sales.service.InvoiceService;
import com.microservice.sales.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;
    
    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private InvoiceFactory factory;

    @Override
    public Invoice generateInvoiceFromQuote(Long quoteId) {
        quote quote = quoteService.getQuotesById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
        return repository.save(factory.createFromQuote(quote));
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return repository.findById(id);
    }
}

