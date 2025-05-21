package com.microservice.planning.entities;

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
@Table(name = "participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int participant_id;
	
	@Column(name = "participant_name")
	private String participant_name;
	
	@Column(name = "participant_last_name")
	private String participant_last_name;
	
	@Column(name = "participant_email")
	private String participant_email;
	
	@Column(name = "participant_phone")
	private String participant_phone;
	
}
