package com.microservice.sales.factory;

import com.microservice.sales.model.Invoice;
import com.microservice.sales.model.InvoiceDetail;
import com.microservice.sales.model.Sale;
import com.microservice.sales.model.quote;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class InvoiceFactory {

    public Invoice createFromQuote(quote quote) {
  
    	Invoice invoice = new Invoice();
        
        // Datos de la factura
        invoice.setIssueDate(new Timestamp(System.currentTimeMillis()));
        invoice.setInvoiceNumber("FAC-" + Instant.now().getEpochSecond());
        invoice.setPaymentMethod(quote.getTypePayment());
        invoice.setTotalAmount(quote.getTotalAmount());
        invoice.setSubtotal(quote.getSubtotal());
        invoice.setTax(quote.getTotalTax());  // Cambiado de 'tax' a 'totalTax' para coincidir con tu modelo
        invoice.setQuote(quote);
          // Nuevo: Relación directa con la venta

        // Copiar detalles de la cotización
        List<InvoiceDetail> invoiceDetails = quote.getDetails().stream()
            .map(quoteDetail -> {
                InvoiceDetail detail = new InvoiceDetail();
                detail.setProductId(quoteDetail.getProductId());
                detail.setProductName(quoteDetail.getProductName()); // Usar método auxiliar
                detail.setQuantity(quoteDetail.getAmount());
                detail.setUnitPrice(quoteDetail.getPrize());
                detail.setTaxRate(quoteDetail.getTax());
                detail.setTotalLine(quoteDetail.getTotal());
                detail.setInvoice(invoice);
                return detail;
            }).collect(Collectors.toList());

        invoice.setDetails(invoiceDetails);
        return invoice;
    }


}