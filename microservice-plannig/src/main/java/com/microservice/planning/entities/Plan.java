package com.microservice.planning.entities;

import java.time.LocalDate;
import java.util.Date;

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
@Table(name = "plan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "plan_id")
	private int plan_id;
	
	@Column(name = "plan_name")
	private String plan_name;
	
	@Column(name = "plan_description")
	private String plan_description;
	
	@Column(name = "plan_start_date")
	private LocalDate plan_start_date;
	
	@Column(name = "plan_end_dates")
	private LocalDate plan_end_date;
	
}
