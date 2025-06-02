	package com.microservice.sales.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

	private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private EmployeeGender gender;
    private EmployeeState state;
    private Long subsidiary;
    private EmployeePosition position;
}
