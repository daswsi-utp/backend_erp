package com.microservice.crm.services;

import com.microservice.crm.dto.ClientStateStats;
import com.microservice.crm.repositories.ClientRepository;
import com.microservice.crm.repositories.ProductMemberRepository;
import com.microservice.crm.entities.ProductMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private ProductMemberRepository productMemberRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Método que obtiene las estadísticas de clientes por estado para todos los productos de un miembro
    public List<ClientStateStats> getSellerStats(Long memberId) {
        List<ProductMember> productMembers = productMemberRepository.findByMemberId(memberId);  // Obtener los productos asociados al miembro
        List<ClientStateStats> allStats = new ArrayList<>();

        // Para cada producto del miembro, obtener las estadísticas de los clientes
        for (ProductMember productMember : productMembers) {
            Long productId = productMember.getProduct().getId();  // Obtener el ID del producto
            // Obtener las estadísticas de los clientes para ese producto y miembro
            List<ClientStateStats> stats = clientRepository.findClientStatsByProductAndMember(productId, memberId);
            allStats.addAll(stats);  // Añadir las estadísticas de ese producto a la lista total
        }

        return allStats;  // Devolver todas las estadísticas agrupadas
    }
}
