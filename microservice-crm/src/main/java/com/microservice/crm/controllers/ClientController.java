package com.microservice.crm.controllers;

import com.microservice.crm.dto.ClientDTO;
import com.microservice.crm.dto.CreateClientDTO;
import com.microservice.crm.services.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    
    @GetMapping("/check")
    public ResponseEntity<?> checkClientExists(
            @RequestParam String phone,
            @RequestParam Long productId) {

        boolean exists = clientService.existsByPhoneAndProduct(phone, productId);

        if (exists) {
            return ResponseEntity.status(409).body("Cliente ya registrado en este producto");
        } else {
            return ResponseEntity.ok("Cliente no encontrado, puede continuar");
        }
    }

    
    private String normalizePhone(String phone, String phoneCode) {
        if (phone == null || phoneCode == null) return null;

        String phoneAux = phone.replaceAll("[^0-9]", "").trim();

        String normalizedPhoneCode = phoneCode.startsWith("+") ? phoneCode : "+" + phoneCode;

        return normalizedPhoneCode + phoneAux;
    }

    
    @GetMapping("/search")
    public ResponseEntity<?> searchClients(
        @RequestParam("search_params") String searchParams,
        @RequestParam("search_filters") List<String> filters
    ) {
        List<ClientDTO> results = clientService.searchClients(searchParams, filters);
        return ResponseEntity.ok().body(Map.of("results", results));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id){
        Optional<ClientDTO> client = clientService.getClient(id);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
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
    
    @PostMapping("/insert-leads")
    public ResponseEntity<?> insertLeads(@RequestBody Map<String, List<CreateClientDTO>> leads) {
        try {
            System.out.println("Datos recibidos: " + leads);
            List<CreateClientDTO> data = leads.get("data");

            System.out.println("Cantidad de clientes recibidos: " + data.size());

            int totalLeads = data.size();
            int peruLeads = (int) data.stream()
                                      .filter(lead -> "Perú".equals(lead.getCountry()) || lead.getPhone().startsWith("+51") || lead.getPhone().length() == 9)
                                      .count();
            int otherLeads = totalLeads - peruLeads;
            int realUsers = 0;

            for (CreateClientDTO lead : data) {
                System.out.println("Procesando cliente: " + lead);

                lead.setMemberId(6L);

                String phone = clientService.normalizePhone(lead.getPhone(), lead.getCountryCode());
                String whatsapp = clientService.normalizePhone(lead.getWhatsapp(), lead.getCountryCode());

                if (!clientService.existsByPhoneAndProduct(phone, lead.getProductId())) {
                    Optional<ClientDTO> createdClient = clientService.createClient(lead);
                    if (createdClient.isPresent()) {
                        realUsers++;
                    }
                }
            }

            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Se registraron con éxito los clientes",
                "total_leads", totalLeads,
                "peru_leads", peruLeads,
                "other_leads", otherLeads,
                "real_users", realUsers
            ));
        } catch (Exception e) {
            System.out.println("Error al registrar los leads: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al registrar los clientes");
        }
    }




    @PutMapping("/{id}/reassign")
    public ResponseEntity<?> reassignClient(
            @PathVariable Long id,
            @RequestBody Map<String, Long> payload) {

        Long newMemberId = payload.get("memberId");
        Long newProductId = payload.get("productId");

        if (newMemberId == null || newProductId == null) {
            return ResponseEntity.badRequest().body("Faltan datos: memberId y productId son requeridos");
        }

        boolean success = clientService.reassignClient(id, newMemberId, newProductId);

        if (success) {
            return ResponseEntity.ok("Cliente reasignado correctamente");
        } else {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }	
    }


}
