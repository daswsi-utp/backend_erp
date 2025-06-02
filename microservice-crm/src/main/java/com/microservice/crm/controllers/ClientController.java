package com.microservice.crm.controllers;

import com.microservice.crm.dto.ClientDTO;
import com.microservice.crm.dto.CreateClientDTO;
import com.microservice.crm.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm/clients")
public class ClientController {

	private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody CreateClientDTO dto){
        ClientDTO created = clientService.createClient(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id){
        Optional<ClientDTO> client = clientService.getClient(id);
        return client.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}