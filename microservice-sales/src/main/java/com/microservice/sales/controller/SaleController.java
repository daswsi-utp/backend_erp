package com.microservice.sales.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.sales.model.DeliveryStatus;
import com.microservice.sales.model.Sale;
import com.microservice.sales.service.InvoiceService;
import com.microservice.sales.service.SaleService;

@RestController
@RequestMapping("/api/v1/sales/transactions")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/from-quote/{quoteId}")
    public ResponseEntity<?> createSaleFromQuote(
            @PathVariable Long quoteId,
            @RequestBody String deliveryAddress) {

        try {
            Sale sale = saleService.createSaleFromQuote(quoteId, deliveryAddress);
            invoiceService.generateInvoiceFromQuote(quoteId, sale); // Asociar y guardar

            return ResponseEntity.ok(sale);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }
}
