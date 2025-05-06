package com.microservice.rrhh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee createEmployee(Employee employee){return employeeRepository.save(employee);}
	
}
