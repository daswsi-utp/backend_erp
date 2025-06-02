package com.microservice.crm.config;

import com.microservice.crm.entities.Team;
import com.microservice.crm.entities.TeamStatus;
import com.microservice.crm.repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner initDefaultTeam(TeamRepository teamRepository) {
	    return args -> {
	        Optional<Team> teamOpt = teamRepository.findByName("Sin Asignar");
	        if (teamOpt.isEmpty()) {
	            Team defaultTeam = new Team();
	            defaultTeam.setName("Sin Asignar");
	            defaultTeam.setStatus(TeamStatus.ACTIVE); // activo

	            teamRepository.save(defaultTeam);
	            System.out.println("Equipo 'Sin Asignar' creado.");
	        } else {
	            System.out.println("Equipo 'Sin Asignar' ya existe.");
	        }
	    };
	}

}
