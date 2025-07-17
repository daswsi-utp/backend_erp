package com.microservice.crm.services;

import com.microservice.crm.dto.LeadAssignmentDTO;
import com.microservice.crm.entities.Client;
import com.microservice.crm.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeadAssignmentService {

    private final ClientRepository clientRepository;

    public LeadAssignmentService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void assignLeads(Long memberId, List<LeadAssignmentDTO> leadAssignments) {
        for (LeadAssignmentDTO assignment : leadAssignments) {
            Long availableLeads = clientRepository.countClientsByProductIdAndState(assignment.getProductId(), 1L);

            List<Client> clients = clientRepository.findByProductIdAndMemberIdAndClientState_Id(
                    assignment.getProductId(),
                    6L,  
                    1L 
            );

            if (availableLeads < assignment.getTotalLeads()) {
                throw new RuntimeException("No hay suficientes leads disponibles para asignar.");
            }

            int assignedLeads = 0;

            for (Client client : clients) {
                if (assignedLeads < assignment.getTotalLeads()) {
                    // Asignar el lead al cliente
                    client.setMemberId(memberId);  // Asignar el `memberId` al cliente
                    clientRepository.save(client);  // Guardar el cliente actualizado
                    assignedLeads++;
                }
            }

            if (assignedLeads < assignment.getTotalLeads()) {
                throw new RuntimeException("No se pudieron asignar todos los leads solicitados.");
            }
        }
    }
}
