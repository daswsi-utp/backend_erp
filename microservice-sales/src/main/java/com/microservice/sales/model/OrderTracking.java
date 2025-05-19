package com.microservice.sales.model;

import java.sql.Timestamp;

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
@Table(name = "order_tracking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTracking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Long id;

	
	
	@Column(name = "supping_date")
	private Timestamp shippingDate; //fecha envio
	
	@Column(name = "delivery_date")
	private Timestamp deliveryDate; //fecha llegada
	
	@Enumerated(EnumType.STRING)
	@Column(name = "delivery_status")
	private DeliveryStatus deliveryStatus;//estado de la entrega
	
	@Column(name = "place_delivery")
	private String placeOdelivery;//lugar de entrega
	
	@OneToOne
	@JoinColumn(name = "quote_id", referencedColumnName = "id_quotes")
	private quote quote;
	
}
