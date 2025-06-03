package com.microservice.crm.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.crm.entities.ClientState;
import com.microservice.crm.repositories.ClientStateRepository;

@RestController
@RequestMapping("/api/v1/crm/client_states")
public class ClientStateController {

    private final ClientStateRepository clientStateRepository;

    public ClientStateController(ClientStateRepository clientStateRepository) {
        this.clientStateRepository = clientStateRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClientState>> getAllClientStates() {
        List<ClientState> states = clientStateRepository.findAll();
        return ResponseEntity.ok(states);
    }
}
