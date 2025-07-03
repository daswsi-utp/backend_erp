package com.microservice.sales.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoice_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice_detail")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoice invoice;

    // Campos copiados desde DetailQuote (o referenciados)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name") // Nuevo campo
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "total_line")
    private Double totalLine;
}