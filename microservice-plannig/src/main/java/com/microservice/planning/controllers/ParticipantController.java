package com.microservice.planning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.planning.entities.Participant;
import com.microservice.planning.services.ParticipantService;

@RestController
@RequestMapping("/")
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantService;
	
	@GetMapping
	public List<Participant> listarAuditorias() {
		return participantService.getParticipant();
	}

	@PostMapping("create")
	public Participant create(@RequestBody Participant participant) {
		return participantService.createParticipant(participant);
	}
	
	@PutMapping("update/{id}")
	public Participant update(@RequestBody Participant participant, @PathVariable Integer id) {
		participant.setParticipant_id(id);
		return participantService.updateParticipant(participant);
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable Integer id) {
		participantService.deleteParticipant(id);
	}
	
}
