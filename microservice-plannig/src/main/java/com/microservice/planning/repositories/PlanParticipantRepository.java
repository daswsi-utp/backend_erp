package com.microservice.planning.repositories;

import com.microservice.planning.entities.PlanParticipant;
import com.microservice.planning.entities.PlanParticipantId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanParticipantRepository extends JpaRepository<PlanParticipant, PlanParticipantId> {

}
