package com.microservice.crm.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.crm.dto.EmployeeDTO;


@FeignClient(name = "msvc-rrhh")
public interface EmployeeFeignClient {

	@GetMapping("/api/v1/rrhh/employee/{id}")
	EmployeeDTO getEmployeeById(@PathVariable("id") Long id);
}
