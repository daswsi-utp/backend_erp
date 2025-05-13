package com.microservice.sales.model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="quote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class quote {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quotes")
	private Long id_quotes;
	
	@Column(name = "issueDate")
	private Timestamp issueDate;
	
	@Column(name = "expirationDate")
	private Timestamp expirationDate;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "id_client")
	private Long id_client;
	
	@Column(name = "id_employee")
	private Long id_employee;
	
	@Column(name = "typePayment")
	private String typePayment;
	
	@Column(name = "observation")
	private String observation;
	
	
}
