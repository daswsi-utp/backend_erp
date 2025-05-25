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

import com.microservice.rrhh.model.Vacation;
import com.microservice.rrhh.model.VacationState;
import com.microservice.rrhh.service.VacationService;

@RestController
@RequestMapping("/api/v1/rrhh/vacation")
public class VacationController {

	@Autowired
	private VacationService vacationService;
	
	@GetMapping
	public ResponseEntity<List<Vacation>> getVacations(){return ResponseEntity.ok(vacationService.getVacations());}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vacation> getVacationById(@PathVariable Long id){
		return vacationService.getVacationById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{vacationState}")
	public ResponseEntity<List<Vacation>> getVcationByState(VacationState vacationState){return ResponseEntity.ok(vacationService.getVacationsByState(vacationState));}
	
	@PostMapping
	public ResponseEntity<Vacation> createVacation(@RequestBody Vacation vacation){return ResponseEntity.ok(vacationService.createVacation(vacation));}
	
	@PutMapping
	public ResponseEntity<Vacation> updateVacation(@RequestBody Vacation vacation){return ResponseEntity.ok(vacationService.updateVacation(vacation));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVacation(@PathVariable Long id){
		vacationService.deleteVacation(id);
		return ResponseEntity.noContent().build();
	}
}
