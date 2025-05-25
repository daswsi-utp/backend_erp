package com.microservice.rrhh.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rrhh.model.Permission;

import com.microservice.rrhh.service.PermissionService;

@RestController
@RequestMapping("api/v1/rrhh/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping
	public ResponseEntity<List<Permission>> getPermissions(){return ResponseEntity.ok(permissionService.getPermissions());}
}
