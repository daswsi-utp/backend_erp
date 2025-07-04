package com.microservice.sales.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Long id;

   
    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;


    @Column(name = "issue_date")
    private Timestamp issueDate;

    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "discount")
    private Double discount;

    @OneToOne
    @JoinColumn(name = "quote_id", referencedColumnName = "id_quotes")
    @JsonBackReference
    private quote quote;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceDetail> details;
}
