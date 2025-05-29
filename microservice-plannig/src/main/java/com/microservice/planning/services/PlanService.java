package com.microservice.planning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.planning.entities.Plan;
import com.microservice.planning.repositories.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;
	
	public List<Plan> getPlans() {
		return planRepository.findAll();
	}
	
	public Plan createPlan(Plan plan) {
		return planRepository.save(plan);
	}
	
	public Plan updatePlan(Plan plan) {
		return planRepository.save(plan);
	}
	
	public void deletePlan(Integer id) {
		planRepository.deleteById(id);
	}
	
}
