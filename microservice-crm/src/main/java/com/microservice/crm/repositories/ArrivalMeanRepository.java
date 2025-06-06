package com.microservice.crm.repositories;

import com.microservice.crm.entities.ArrivalMean;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalMeanRepository extends JpaRepository<ArrivalMean, Long> {
	Optional<ArrivalMean> findBySlug(String slug);

}
