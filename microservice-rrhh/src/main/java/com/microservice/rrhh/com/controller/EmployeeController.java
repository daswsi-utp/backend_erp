package com.microservice.rrhh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.model.EmployeePosition;
import com.microservice.rrhh.model.EmployeeState;
import com.microservice.rrhh.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/rrhh/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees(){return ResponseEntity.ok(employeeService.getEmployees());}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getDepartmentById(@PathVariable Long id){
		return employeeService.getEmployeeById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable Long id){return ResponseEntity.ok(employeeService.getEmployeesByDepartmentId(id));}
	
	@GetMapping("/state/{employeeState}")
	public ResponseEntity<List<Employee>> getEmployeeByState(@PathVariable EmployeeState employeeState){return ResponseEntity.ok(employeeService.getEmployeeByState(employeeState));}
	
	@GetMapping("/position/{employeePosition}")
	public ResponseEntity<List<Employee>> getEmployeeByPosition(@PathVariable EmployeePosition employeePosition){return ResponseEntity.ok(employeeService.getEmployeeByPosition(employeePosition));}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {return employeeService.createEmployee(employee);}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){return ResponseEntity.ok(employeeService.updateEmployee(employee));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

}
