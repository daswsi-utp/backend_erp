package com.microservice.crm.repositories;

import com.microservice.crm.entities.ClientState;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientStateRepository extends JpaRepository<ClientState, Long> {
	Optional<ClientState> findBySlug(String slug);
	
}
