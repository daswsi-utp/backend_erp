package com.microservice.sales.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "detail_quote")
public class DetailQuote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detailQuote")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "quotes_id", nullable = false)
	private quote quoteId;
	
	@Column(name = "id_product")
	private Long productId;
	
	@Column(name = "amount") //cantidad
	private Integer amount;
	
	@Column(name = "discount")//descuento
	private Integer discount;
	
	@Column(name = "tax")//impuesto
	private Integer tax;
	
	@Column(name = "total")
	private Integer total;
	
	
}
