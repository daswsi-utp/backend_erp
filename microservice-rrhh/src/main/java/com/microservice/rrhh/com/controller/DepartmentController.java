package com.microservice.rrhh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Department;
import com.microservice.rrhh.service.DepartmentService;

@RestController
@RequestMapping("/api/v1/rrhh/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<List<Department>> getDepartments(){return ResponseEntity.ok(departmentService.getDepartments());}
	
	@PostMapping
	public Department createDepartment(@RequestBody Department department){return departmentService.createDepartment(department);}
	
}
