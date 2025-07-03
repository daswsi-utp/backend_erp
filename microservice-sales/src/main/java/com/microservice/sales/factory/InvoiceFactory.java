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

    public Invoice createFromQuote(quote quote, Sale sale) {
        Invoice invoice = new Invoice();

        invoice.setIssueDate(new Timestamp(System.currentTimeMillis()));
        invoice.setInvoiceNumber("FAC-" + Instant.now().getEpochSecond());
        invoice.setPaymentMethod(quote.getTypePayment());
        invoice.setTotalAmount(quote.getTotalAmount());
        invoice.setSubtotal(quote.getSubtotal());
        invoice.setTax(quote.getTotalTax());
        invoice.setQuote(quote);
        invoice.setSale(sale);

        List<InvoiceDetail> details = quote.getDetails().stream()
                .map(quoteDetail -> {
                    InvoiceDetail detail = new InvoiceDetail();
                    detail.setProductId(quoteDetail.getProductId());
                    detail.setProductName(quoteDetail.getProductName());
                    detail.setQuantity(quoteDetail.getAmount());
                    detail.setUnitPrice(quoteDetail.getPrize());
                    detail.setTaxRate(quoteDetail.getTax());
                    detail.setTotalLine(quoteDetail.getTotal());
                    detail.setInvoice(invoice);
                    return detail;
                }).collect(Collectors.toList());

        invoice.setDetails(details);
        return invoice;
    }
}
