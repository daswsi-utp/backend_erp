package com.microservice.rrhh.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
	
	public Vacation createVacation(Vacation vacation){
		Long days = daysTakenCalculator(vacation.getStartDate(), vacation.getEndDate());
		vacation.setDaysTaken(days);
		return vacationRepository.save(vacation);
	}
	
	public Vacation updateVacation(Vacation vacation) {
		Long days = daysTakenCalculator(vacation.getStartDate(), vacation.getEndDate());
		vacation.setDaysTaken(days);
		return vacationRepository.save(vacation);
	}
	
	public void deleteVacation(Long id){vacationRepository.deleteById(id);}
	
	
	public static long daysTakenCalculator(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
	
}
