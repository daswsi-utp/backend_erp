package com.microservice.sales.controller;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @PostMapping("/from-quote/{quoteId}")
    public ResponseEntity<Invoice> createFromQuote(@PathVariable Long quoteId) {
        return ResponseEntity.ok(service.generateInvoiceFromQuote(quoteId));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAll() {
        return ResponseEntity.ok(service.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
        return service.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

