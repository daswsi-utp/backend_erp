package com.microservice.rrhh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{

}
