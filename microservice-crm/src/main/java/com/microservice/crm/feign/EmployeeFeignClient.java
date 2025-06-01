package com.microservice.crm.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "rrhh-service", url = "${rrhh.service.url}")
public interface EmployeeFeignClient {

    @GetMapping("/api/v1/rrhh/employees/{id}")
    Map<String, Object> getEmployeeById(@PathVariable("id") Long id);
}
