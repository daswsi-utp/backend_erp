package com.microservice.planning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.planning.entities.Participant;
import com.microservice.planning.repositories.ParticipantRepository;

@Service
public class ParticipantService {
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	public List<Participant> getParticipant() {
		return participantRepository.findAll();
	}
	
	public Participant createParticipant(Participant participant) {
		return participantRepository.save(participant);
	}
	
	public Participant updateParticipant(Participant participant) {
		return participantRepository.save(participant);
	}
	
	public void deleteParticipant(Integer id) {
		participantRepository.deleteById(id);
	}
}
