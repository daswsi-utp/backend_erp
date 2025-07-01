package com.microservice.sales.factory;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.model.quote;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Component;

@Component
public class InvoiceFactory {
	
	
	 public Invoice createFromQuote(quote quote) {
    	 Invoice invoice = new Invoice();
	        invoice.setIssueDate(new Timestamp(System.currentTimeMillis()));
	        invoice.setInvoiceNumber("FAC-" + Instant.now().getEpochSecond());
	        invoice.setPaymentMethod(quote.getTypePayment());
	        invoice.setTotalAmount(quote.getTotalAmount());
	        invoice.setSubtotal(quote.getSubtotal());
	        invoice.setTax(quote.getTotalTax());
	        invoice.setDiscount(quote.getTotalDiscount());
	        invoice.setQuote(quote);
        return invoice;
    }

}
