package com.microservice.rrhh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
