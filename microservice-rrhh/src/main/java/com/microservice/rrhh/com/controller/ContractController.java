package com.microservice.rrhh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Contract;
import com.microservice.rrhh.service.ContractService;

@RestController
@RequestMapping("/api/v1/rrhh/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping
	public ResponseEntity<List<Contract>> getContracts(){return ResponseEntity.ok(contractService.getContracts());}
	
}
