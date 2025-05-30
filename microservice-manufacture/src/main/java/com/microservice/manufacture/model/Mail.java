package com.microservice.manufacture.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table( name = "mail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

	@ElementCollection
	@CollectionTable(name = "mail_recipients", joinColumns = @JoinColumn(name = "mail_id"))
	@Column(name = "recipient")
	private List<String> to;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "body")
	private String body;
	
}
