package com.microservice.crm.services;

import org.springframework.stereotype.Service;
import com.microservice.crm.dto.ProductLeadsDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.ArrayList;

@Service
public class ProductMemberService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductLeadsDTO> getLeadsForMember(Long memberId) {
        String query = "SELECT p.id, p.name FROM Product p " +
                       "JOIN ProductMember pm ON pm.product.id = p.id " +
                       "WHERE pm.member.id = :memberId";
        List<Object[]> productAssignments = entityManager.createQuery(query)
                                                        .setParameter("memberId", memberId)
                                                        .getResultList();

        List<ProductLeadsDTO> productLeads = new ArrayList<>();

        for (Object[] productAssignment : productAssignments) {
            Long productId = (Long) productAssignment[0];
            String productName = (String) productAssignment[1];

            Long totalLeads = getTotalLeadsForProduct(productId, memberId);

            Long availableLeads = getAvailableLeadsForProduct(productId, 6L);  // Se utiliza el memberId 6 para obtener los leads disponibles

            ProductLeadsDTO dto = new ProductLeadsDTO(productId, productName, totalLeads, availableLeads);
            productLeads.add(dto);
        }

        return productLeads;
    }

    public Long getTotalLeadsForProduct(Long productId, Long memberId) {
        String query = "SELECT COUNT(c.id) FROM Client c " +
                       "WHERE c.product.id = :productId " +
                       "AND c.member.id = :memberId";  // Filtra por productId y memberId
        return (Long) entityManager.createQuery(query)
                                   .setParameter("productId", productId)
                                   .setParameter("memberId", memberId)  
                                   .getSingleResult();
    }

    public Long getAvailableLeadsForProduct(Long productId, Long memberId) {
        String query = "SELECT COUNT(c.id) FROM Client c " +
                       "WHERE c.product.id = :productId " +
                       "AND c.clientState.id = 1 " +
                       "AND c.member.id = :memberId";  // Añadimos el filtro por memberId
        return (Long) entityManager.createQuery(query)
                                   .setParameter("productId", productId)
                                   .setParameter("memberId", memberId)  
                                   .getSingleResult();
    }
}


