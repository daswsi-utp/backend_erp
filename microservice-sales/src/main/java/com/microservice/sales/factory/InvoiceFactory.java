// Nuevo archivo: InvoiceFactory.java
package com.microservice.sales.factory;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.model.quote;

import java.sql.Timestamp;
import java.time.Instant;

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

    // agregar más métodos factory para diferentes tipos de facturas
    public static Invoice createProformaInvoice(quote quote) {
        Invoice invoice = createStandardInvoice(quote);
        invoice.setInvoiceNumber("PRO-" + Instant.now().getEpochSecond());
        
        return invoice;
    }
}
