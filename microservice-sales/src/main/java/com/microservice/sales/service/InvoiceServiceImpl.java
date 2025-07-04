// InvoiceServiceImpl.java
package com.microservice.sales.service;

import com.microservice.sales.factory.InvoiceFactory;
import com.microservice.sales.model.*;
import com.microservice.sales.repository.InvoiceRepository;
import com.microservice.sales.repository.SaleRepository;
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

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Invoice generateInvoiceFromQuote(Long quoteId, Sale sale) {
        quote quote = quoteService.getQuotesById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        Invoice invoice = factory.createFromQuote(quote, sale);
        sale.setInvoice(invoice); // Establece la relación bidireccional

        saleRepository.save(sale); // Gracias al cascade, se guarda la factura también
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Invoice> getInvoiceBySaleId(Long saleId) {
        return repository.findBySaleId(saleId);
    }

}
