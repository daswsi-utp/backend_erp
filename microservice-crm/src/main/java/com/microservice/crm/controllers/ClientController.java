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
    public ResponseEntity<?> createClient(@RequestBody CreateClientDTO dto){
        Optional<ClientDTO> created = clientService.createClient(dto);
        if (created.isPresent()) {
            return ResponseEntity.ok(created.get());
        } else {
            return ResponseEntity.status(422).body("Client with this phone and product already exists");
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkClientExists(
            @RequestParam String phone,
            @RequestParam Long productId) {

        boolean exists = clientService.existsByPhoneAndProduct(phone, productId);

        if (exists) {
            return ResponseEntity.status(409).body("Cliente ya registrado en este producto");
        } else {
            return ResponseEntity.ok().body("Cliente no encontrado, puede continuar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id){
        Optional<ClientDTO> client = clientService.getClient(id);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
}
