package com.microservice.crm.config;

import com.microservice.crm.entities.ClientState;
import com.microservice.crm.entities.Reason;
import com.microservice.crm.entities.ArrivalMean;
import com.microservice.crm.repositories.ClientStateRepository;
import com.microservice.crm.repositories.ReasonRepository;
import com.microservice.crm.repositories.ArrivalMeanRepository;
import com.microservice.crm.entities.Team;
import com.microservice.crm.entities.TeamStatus;
import com.microservice.crm.repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                defaultTeam.setStatus(TeamStatus.ACTIVE);
                teamRepository.save(defaultTeam);
                System.out.println("Equipo 'Sin Asignar' creado.");
            } else {
                System.out.println("Equipo 'Sin Asignar' ya existe.");
            }
        };
    }

    @Bean
    public CommandLineRunner initClientStates(ClientStateRepository clientStateRepository) {
        return args -> {
            String[][] states = {
                {"Nuevo Cliente", "NC"},
                {"Cliente Interesado", "CI"},
                {"Cliente Potencial", "CP"},
                {"Envio de Ficha", "EF"},
                {"Matriculado", "M"},
                {"No Interesado", "NI"}
            };

            for (String[] state : states) {
                Optional<ClientState> existing = clientStateRepository.findBySlug(state[1]);
                if (existing.isEmpty()) {
                    ClientState cs = new ClientState();
                    cs.setName(state[0]);
                    cs.setSlug(state[1]);
                    clientStateRepository.save(cs);
                    System.out.println("ClientState " + state[0] + " creado.");
                }
            }
        };
    }

    @Bean
    public CommandLineRunner initReasons(ReasonRepository reasonRepository) {
        return args -> {
            // Puedes definir aquí razones genéricas o comunes
            String[][] reasons = {
                {"Razón Genérica", "generic"},
                {"Motivo Comercial", "commercial"},
                {"Otra Razón", "other"}
            };

            for (String[] reason : reasons) {
                Optional<Reason> existing = reasonRepository.findBySlug(reason[1]);
                if (existing.isEmpty()) {
                    Reason r = new Reason();
                    r.setName(reason[0]);
                    r.setSlug(reason[1]);
                    reasonRepository.save(r);
                    System.out.println("Reason " + reason[0] + " creado.");
                }
            }
        };
    }

    @Bean
    public CommandLineRunner initArrivalMeans(ArrivalMeanRepository arrivalMeanRepository) {
        return args -> {
            String[][] means = {
                {"Sin Asignar", "S/A"},
                {"Facebook", "FB"},
                {"WhatsApp", "WSP"},
                {"Base Histórica", "RC"}
            };

            for (String[] mean : means) {
                Optional<ArrivalMean> existing = arrivalMeanRepository.findBySlug(mean[1]);
                if (existing.isEmpty()) {
                    ArrivalMean am = new ArrivalMean();
                    am.setName(mean[0]);
                    am.setSlug(mean[1]);
                    arrivalMeanRepository.save(am);
                    System.out.println("ArrivalMean " + mean[0] + " creado.");
                }
            }
        };
    }

}
