package com.microservice.rrhh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Contract;
import com.microservice.rrhh.repository.ContractRepository;

@Service
public class ContractService {
	
	@Autowired
	private ContractRepository contractRepository;
	
	public List<Contract> getContracts(){return contractRepository.findAll();}
	
}
