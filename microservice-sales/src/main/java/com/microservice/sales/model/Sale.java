package com.microservice.sales.model;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "sale")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Sale {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sale")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "quote_id")
	private quote quote;
	
	@Column(name = "sale_date")
	private Timestamp saleDate;
	
	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "delivery_status")
	private DeliveryStatus deliveryStatus;
	
	@OneToOne(mappedBy = "sale", cascade = CascadeType.ALL)
    private Invoice invoice;
	

}
