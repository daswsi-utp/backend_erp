package com.microservice.rrhh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}
