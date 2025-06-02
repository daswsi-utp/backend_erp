package com.microservice.rrhh.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Permission;
import com.microservice.rrhh.model.PermissionState;
import com.microservice.rrhh.model.PermissionType;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	List<Permission> findByState(PermissionState permissionState);
	List<Permission> findByType(PermissionType permissionType);
}
