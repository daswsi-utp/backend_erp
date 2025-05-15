package com.microservice.rrhh.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.rrhh.model.Contract;
import com.microservice.rrhh.repository.ContractRepository;

@Service
public class ContractService {
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private FileService fileService;
	
	String url = "http://localhost:8095/files/";
	
	public List<Contract> getContracts(){
		List<Contract> contracts = contractRepository.findAll();
		contracts = addUrl(contracts);
		return contracts;
	}
	
	public Optional<Contract> getContractById(Long id){
		Optional<Contract> contract = contractRepository.findById(id);
		if (contract.isPresent()) {
	        Contract c = contract.get();
	        c.setKey(url + c.getKey());
	        return Optional.of(c);
	    }
	    return contract;
	}
	
	public Contract createContract(Contract contract, MultipartFile file) throws IOException{
		String key = fileService.saveFile(file);
		contract.setKey(key);
		return contractRepository.save(contract);
	}
	
	private List<Contract> addUrl(List<Contract> contracts){
		contracts = contracts.stream().map(contract -> {contract.setKey(url + contract.getKey());
			return contract;
		}).collect(Collectors.toList());
		return contracts;
	}
	
}
