package com.microservice.rrhh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Permission;
import com.microservice.rrhh.model.PermissionState;
import com.microservice.rrhh.model.PermissionType;
import com.microservice.rrhh.service.PermissionService;

@RestController
@RequestMapping("api/v1/rrhh/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping
	public ResponseEntity<List<Permission>> getPermissions(){return ResponseEntity.ok(permissionService.getPermissions());}
	
	@GetMapping("/state/{permissionState}")
	public ResponseEntity<List<Permission>> getPermissionByState(@PathVariable PermissionState permissionState){return ResponseEntity.ok(permissionService.getPermissionByState(permissionState));}
	
	@GetMapping("/type/{permissionType}")
	public ResponseEntity<List<Permission>> getPermissionByType(@PathVariable PermissionType permissionType){return ResponseEntity.ok(permissionService.getPermissionByType(permissionType));}
	
	@GetMapping("/{id}")
	public ResponseEntity<Permission> getPermissionById(@PathVariable Long id){
		return permissionService.getPermissionById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){return ResponseEntity.ok(permissionService.createPermission(permission));}
	
	@PutMapping
	public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission){return ResponseEntity.ok(permissionService.updatePermission(permission));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePermission(@PathVariable Long id){
		permissionService.deletePermission(id);
		return ResponseEntity.noContent().build();
	}
	
}
