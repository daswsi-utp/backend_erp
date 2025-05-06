package com.microservice.rrhh.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Department;
import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/rrhh/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {return employeeService.createEmployee(employee);}

}
