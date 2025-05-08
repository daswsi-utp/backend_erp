package com.microservice.rrhh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.model.EmployeePosition;
import com.microservice.rrhh.model.EmployeeState;
import com.microservice.rrhh.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployees(){return employeeRepository.findAll();}
	
	public Optional<Employee> getEmployeeById(Long id) {return employeeRepository.findById(id);}
	
	public List<Employee> getEmployeesByDepartmentId(Long id){return employeeRepository.findAllByDepartment_Id(id);}
	
	public List<Employee> getEmployeeByState(EmployeeState employeeState){return employeeRepository.findByState(employeeState);}
	
	public List<Employee> getEmployeeByPosition(EmployeePosition employeePosition){return employeeRepository.findByPosition(employeePosition);}
	
	public Employee createEmployee(Employee employee){return employeeRepository.save(employee);}
	
	public Employee updateEmployee(Employee employee){return employeeRepository.save(employee);}
	
	public void deleteEmployee(Long id){employeeRepository.deleteById(id);}
	
}
