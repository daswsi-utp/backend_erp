package com.microservice.rrhh.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "birthDate")
	private LocalDate birthDate;
	
	@Column(name = "emergencyName")
	private String emergencyContactName;
	
	@Column(name = "emergencyPhone")
	private String emergencyContactPhone;
	
	@Column(name = "gender")
	private EmployeeGender gender;
	
	@Column(name = "state")
	private EmployeeState state;
	
	@Column(name = "position")
	private EmployeePosition position;
	
	
	
}
