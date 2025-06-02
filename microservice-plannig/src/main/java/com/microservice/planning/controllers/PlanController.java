package com.microservice.planning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.planning.entities.Plan;
import com.microservice.planning.services.PlanService;

@RestController
@RequestMapping("/api/v1/planning/plan")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping
	public List<Plan> listPlans() {
		return planService.getPlans();
	}

	@PostMapping("create")
	public Plan create(@RequestBody Plan plan) {
		return planService.createPlan(plan);
	}
	
	@PutMapping("update/{id}")
	public Plan update(@RequestBody Plan plan, @PathVariable Integer id) {
		plan.setPlan_id(id);
		return planService.updatePlan(plan);
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable Integer id) {
		planService.deletePlan(id);
	}
	
	@GetMapping("{id}")
	public Plan getById(@PathVariable Integer id) {
		return planService.getPlanById(id);
	}
}
