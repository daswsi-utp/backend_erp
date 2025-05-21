package com.microservice.planning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.planning.entities.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer>{

}
