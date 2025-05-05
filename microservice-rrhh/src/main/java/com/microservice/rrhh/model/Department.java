package com.microservice.rrhh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "department" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "id" )
	private Long id;

	@Column( name = "name" )
	private String name;
	
	@Column( name = "code" )
	private String code;
	
	@Column( name = "state" )
	private DepartmentState state;
	
	
}
