package com.microservice.sales.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.sales.dto.EmployeeDTO;


@FeignClient(name = "msvc-rrhh", url = "http://localhost:8091/api/v1/rrhh/employee")
public interface EmployeeClient {
	
	//@GetMapping("/position/{employeePosition}")  // 👈 Mismo endpoint que en el otro microservicio
	//List<Employee> getEmployeesByPosition(@PathVariable("employeePosition") String employeePosition);
}
