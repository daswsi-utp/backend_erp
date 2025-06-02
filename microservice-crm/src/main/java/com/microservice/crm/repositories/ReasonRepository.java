package com.microservice.crm.repositories;

import com.microservice.crm.entities.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<Reason, Long> {
}
