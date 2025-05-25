package com.microservice.rrhh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Permission;
import com.microservice.rrhh.model.PermissionState;
import com.microservice.rrhh.model.PermissionType;
import com.microservice.rrhh.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> getPermissions(){return permissionRepository.findAll();}
	
	public List<Permission> getPermissionByState(PermissionState permissionState){return permissionRepository.findByState(permissionState);}
	
	public List<Permission> getPermissionByType(PermissionType permissionType){return permissionRepository.findByType(permissionType);}
	
	public Optional<Permission> getPermissionById(Long id){return permissionRepository.findById(id);}
}
