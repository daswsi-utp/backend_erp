package com.microservice.planning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.planning.entities.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>{

}
