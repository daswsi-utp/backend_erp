package com.microservice.rrhh.com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.rrhh.model.Contract;
import com.microservice.rrhh.service.ContractService;

@RestController
@RequestMapping("/api/v1/rrhh/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping
	public ResponseEntity<List<Contract>> getContracts(){return ResponseEntity.ok(contractService.getContracts());}
	
	@PostMapping
	public ResponseEntity<Contract> createContract(@RequestPart MultipartFile file, @RequestPart Contract contract) throws IOException{
		return new ResponseEntity<>(contractService.createContract(contract, file), HttpStatus.CREATED);
	}
}
