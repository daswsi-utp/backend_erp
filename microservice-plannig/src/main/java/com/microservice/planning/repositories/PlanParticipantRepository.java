package com.microservice.planning.repositories;

import com.microservice.planning.entities.PlanParticipant;
import com.microservice.planning.entities.PlanParticipantId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanParticipantRepository extends JpaRepository<PlanParticipant, PlanParticipantId> {
	List<PlanParticipant> findByPlan_PlanId(int planId);
}
