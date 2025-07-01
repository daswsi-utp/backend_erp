// Nuevo archivo: InvoiceFactory.java
package com.microservice.sales.factory;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.model.quote;

import java.sql.Timestamp;


public class InvoiceFactory {
    
    public static Invoice createStandardInvoice(quote quote) {
    	 Invoice invoice = new Invoice();
	        invoice.setIssueDate(new Timestamp(System.currentTimeMillis()));
	        invoice.setInvoiceNumber("INV-" + System.currentTimeMillis()); // Generar número único
	        invoice.setPaymentMethod(quote.getTypePayment());
	        invoice.setTotalAmount(quote.getTotalAmount());
	        invoice.setSubtotal(quote.getSubtotal());
	        invoice.setTax(quote.getTotalTax());
	        invoice.setDiscount(quote.getTotalDiscount());
	        invoice.setQuote(quote);
        return invoice;
    }

}
