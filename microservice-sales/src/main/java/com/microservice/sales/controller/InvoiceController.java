package com.microservice.sales.controller;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate-from-quote/{quoteId}")
    public ResponseEntity<Invoice> generateInvoiceFromQuote(@PathVariable Long quoteId) {
        return ResponseEntity.ok(invoiceService.generateInvoiceFromQuote(quoteId));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/update-status")
    public ResponseEntity<Invoice> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {
        return ResponseEntity.ok(invoiceService.updatePaymentStatus(id, status));
    }
}

