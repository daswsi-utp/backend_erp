package com.microservice.crm.controllers;

import com.microservice.crm.dto.ClientDTO;
import com.microservice.crm.dto.CreateClientDTO;
import com.microservice.crm.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<ClientDTO> clients = clientService.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener los clientes");
        }
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

        String normalizedPhone = normalizePhone(phone, "+51"); 
        boolean exists = clientService.existsByPhoneAndProduct(normalizedPhone, productId);

        if (exists) {
            return ResponseEntity.status(409).body("Cliente ya registrado en este producto");
        } else {
            return ResponseEntity.ok("Cliente no encontrado, puede continuar");
        }
    }
    
    private String normalizePhone(String phone, String countryCode) {
        if (phone == null) return null;
        String phoneAux = phone.replaceAll("\\s", "").trim();

        if (!phoneAux.startsWith("+")) {
            if (phoneAux.matches("^[531].*")) {
                phoneAux = "+" + phoneAux;
            } else if (countryCode != null && !countryCode.isEmpty()) {
                phoneAux = countryCode + phoneAux;
            }
        }
        return phoneAux;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id){
        Optional<ClientDTO> client = clientService.getClient(id);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
}
