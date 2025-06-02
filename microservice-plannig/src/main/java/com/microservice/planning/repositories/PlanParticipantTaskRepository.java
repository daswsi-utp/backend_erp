package com.microservice.planning.repositories;

import com.microservice.planning.entities.PlanParticipantTask;
import com.microservice.planning.entities.PlanParticipantTaskId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanParticipantTaskRepository extends JpaRepository<PlanParticipantTask, PlanParticipantTaskId> {

}
