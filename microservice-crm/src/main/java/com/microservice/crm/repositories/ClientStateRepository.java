package com.microservice.crm.repositories;

import com.microservice.crm.entities.ClientState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientStateRepository extends JpaRepository<ClientState, Long> {
}
