package com.microservice.rrhh.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vacation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "startDate")
	private LocalDate startDate;
	
	@Column(name = "endDate")
	private LocalDate endDate;
	
	@Column(name = "daysTaken")
	private Long daysTaken;
	
	@Column(name = "state")
	private VacationState state;
	
	@Column(name = "requestAt")
	private LocalDate requestAt;
	
}
