package com.microservice.rrhh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Role;
import com.microservice.rrhh.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getRoles(){return roleRepository.findAll();}
	
	public Optional<Role> getRoleById(Long id){return roleRepository.findById(id);}
	
	
	
}
