package com.microservice.rrhh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Department;
import com.microservice.rrhh.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getDepartments(){return departmentRepository.findAll();}
	
	public Optional<Department> getDepartmentById(Long id) {return departmentRepository.findById(id);}
	
	public Department createDepartment(Department department){return departmentRepository.save(department);}

	public Department updateDepartment(Department department){return departmentRepository.save(department);}

	public void deleteDepartment(Long id){departmentRepository.deleteById(id);}

}
