package com.microservice.rrhh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Role;
import com.microservice.rrhh.service.RoleService;

@RestController
@RequestMapping("/api/v1/rrhh/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<Role>> getRoles(){return ResponseEntity.ok(roleService.getRoles());}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable Long id){
		return roleService.getRoleById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody Role role){return ResponseEntity.ok(roleService.createRole(role));}
	
}
