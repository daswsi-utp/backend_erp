package com.microservice.rrhh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Employee;
import com.microservice.rrhh.model.EmployeeState;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDepartment_Id(Long id);
	List<Employee> findByState(EmployeeState state);
}
