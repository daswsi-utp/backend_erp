package com.microservice.sales.service;

import java.util.List;
import java.util.Optional;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.model.Sale;


public interface InvoiceService {
    Invoice generateInvoiceFromQuote(Long quoteId, Sale sale);
    List<Invoice> getAllInvoices();
    Optional<Invoice> getInvoiceById(Long id);
    Optional<Invoice> getInvoiceBySaleId(Long saleId);

}
