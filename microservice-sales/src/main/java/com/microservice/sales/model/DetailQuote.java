package com.microservice.sales.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detail_quote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DetailQuote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detail_quote")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_quotes")
	@JsonBackReference
	private quote quoteId;
	
	@Column(name = "id_product")
	private Long productId;
	
	@Column(name = "amount") //cantidad
	private Integer amount;
	
	@Column(name = "prize")//precio
	private Double prize;
	
	@Column(name = "discount")//descuento
	private Double discount;
	
	@Column(name = "tax")//impuesto
	private Double tax;
	
	@Column(name = "total")//total
	private Double total;
	
	//IMPLEMENTAR COMMIT (PRUEBA TOTAL)
	
	
}
