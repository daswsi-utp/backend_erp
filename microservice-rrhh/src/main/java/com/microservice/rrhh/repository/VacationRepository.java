package com.microservice.rrhh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rrhh.model.Vacation;
import com.microservice.rrhh.model.VacationState;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
	List<Vacation> findByState(VacationState vacationState);
}
