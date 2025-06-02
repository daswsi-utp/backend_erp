package com.microservice.sales.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.sales.model.DeliveryStatus;
import com.microservice.sales.model.Sale;
import com.microservice.sales.model.State;
import com.microservice.sales.model.quote;
import com.microservice.sales.repository.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
    private SaleRepository saleRepository;

    @Autowired
    private QuoteService quoteService;
    
    public Sale createSaleFromQuote(Long quoteId, String deliveryAddress) {
        quote quote = quoteService.getQuotesById(quoteId)
            .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        if (quote.getState() != State.APPROVED) {
            throw new IllegalStateException("Solo se pueden convertir cotizaciones aprobadas en ventas.");
        }

        Sale sale = new Sale();
        sale.setQuote(quote);
        sale.setSaleDate(new Timestamp(System.currentTimeMillis()));
        sale.setDeliveryAddress(deliveryAddress);
        sale.setDeliveryStatus(DeliveryStatus.PACKAGED);

        return saleRepository.save(sale);
    }
    
    public Sale updateStatus(Long saleId, DeliveryStatus status) {
        Sale sale = saleRepository.findById(saleId)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        sale.setDeliveryStatus(status);
        return saleRepository.save(sale);
    }

	

}
