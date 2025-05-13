package com.microservice.sales.model;

import java.security.Timestamp;

import org.hibernate.annotations.ManyToAny;

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
@Table(name="quote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class quote {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quotes")
	private Long id;
	
	@Column(name = "issue_date")
	private Timestamp issueDate;
	
	@Column(name = "expiration_date")
	private Timestamp expirationDate;
	
	@Column(name = "state")
	private String state;
		

	@Column(name = "id_client")
	private Long clientId;
	
	
	@Column(name = "id_employee")
	private Long employeeId;
	
	@Column(name = "typePayment")
	private String typePayment;
	
	@Column(name = "observation")
	private String observation;
	
	
}
