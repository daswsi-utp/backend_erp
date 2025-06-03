package com.microservice.planning.entities;

import java.time.LocalDateTime;
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
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private int task_id;
	
	@Column(name = "task_name")
	private String task_name;
	
	@Column(name = "task_description")
	private String task_description;
	
	@Column(name = "task_start_date")
	private LocalDateTime task_start_date;
	
	@Column(name = "task_end_date")
	private LocalDateTime task_end_date;
	
}
