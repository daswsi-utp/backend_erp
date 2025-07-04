package com.microservice.crm.controllers;

import com.microservice.crm.dto.AssignmentDTO;
import com.microservice.crm.entities.Client;
import com.microservice.crm.repositories.ClientRepository;
import com.microservice.crm.services.LeadsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/crm/leads")
public class LeadsController {

    private final LeadsService leadsService;
    private final ClientRepository clientRepository;

    public LeadsController(LeadsService leadsService, ClientRepository clientRepository) {
        this.leadsService = leadsService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/members-and-bags/{productCode}")
    public Map<String, Object> getMembersAndBags(@PathVariable String productCode) {
        return leadsService.getMembersAndBags(productCode);
    }

    @PutMapping("/assign_leads")
    public ResponseEntity<?> assignLeads(@RequestBody List<AssignmentDTO> assignments, @RequestParam String productCode) {
        // Consulta los clientes no asignados
        List<Client> unassignedClients = clientRepository.findByProductIdAndMemberIdAndClientState_Id(6L, 1L, 1L); // Estado 1 para activos

        int totalClientsToAssign = unassignedClients.size();
        int totalAssigned = 0;

        // Asignamos los leads a los miembros seleccionados
        for (AssignmentDTO assignment : assignments) {
            Long userId = assignment.getUser_id();
            int totalAssign = assignment.getTotal_assign();

            // Verificar si hay suficientes clientes disponibles
            if (totalAssigned + totalAssign > totalClientsToAssign) {
                return ResponseEntity.badRequest().body("No hay suficientes clientes para asignar");
            }

            for (int i = totalAssigned; i < totalAssigned + totalAssign; i++) {
                Client client = unassignedClients.get(i);
                client.setMemberId(userId);  // Asignar el miembro
                clientRepository.save(client);  // Guardar los cambios
            }

            totalAssigned += totalAssign;  
        }

        return ResponseEntity.ok("Clientes asignados correctamente");
    }
}
