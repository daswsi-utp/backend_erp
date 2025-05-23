package com.microservice.rrhh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rrhh.model.Vacation;
import com.microservice.rrhh.model.VacationState;
import com.microservice.rrhh.repository.VacationRepository;

@Service
public class VacationService {
	
	@Autowired
	private VacationRepository vacationRepository;
	
	public List<Vacation> getVacations(){return vacationRepository.findAll();}
	
	public Optional<Vacation> getVacationById(Long id){return vacationRepository.findById(id);}
	
	public List<Vacation> getVacationsByState(VacationState vacationState){return vacationRepository.findByState(vacationState);}
	
}
