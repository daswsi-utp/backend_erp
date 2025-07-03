package com.microservice.crm.repositories;

import com.microservice.crm.dto.ClientStateStats;  // Asegúrate de que esté importado correctamente

import com.microservice.crm.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPhoneAndProductId(String phone, Long productId);
    List<Client> findByPhone(String phone);
    List<Client> findByPhoneContaining(String searchTerm);
    List<Client> findByWhatsappContaining(String searchTerm);
    List<Client> findByPhoneContainingOrWhatsappContaining(String phoneTerm, String whatsappTerm);
    List<Client> findByProductIdAndMemberId(Long productId, Long memberId);

    @Query("SELECT new com.microservice.crm.dto.ClientStateStats(c.clientState.name, COUNT(c), COUNT(DISTINCT c.id), c.clientState.slug, c.clientState.id) " +
    	       "FROM Client c WHERE c.memberId = :sellerId " +
    	       "GROUP BY c.clientState.name, c.clientState.slug, c.clientState.id")
    	List<ClientStateStats> findCallsAndUniqueClientsByState(Long sellerId);

    @Query("SELECT new com.microservice.crm.dto.ClientStateStats(c.clientState.name, COUNT(c), COUNT(DISTINCT c.id), c.clientState.slug, c.clientState.id) " +
    	       "FROM Client c WHERE c.product.id = :productId AND c.memberId = :memberId " +
    	       "GROUP BY c.clientState.name, c.clientState.slug, c.clientState.id")
    List<ClientStateStats> findClientStatsByProductAndMember(Long productId, Long memberId);

    List<Client> findByProductIdAndMemberIdAndClientState_Id(Long productId, Long memberId, Long clientStateId);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.product.id = :productId AND c.clientState.id = :clientStateId")
    Long countClientsByProductIdAndState(Long productId, Long clientStateId);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.product.id = :productId")
    Long countTotalLeadsForProduct(Long productId);
    
    
    @Query("SELECT COUNT(c) FROM Client c WHERE c.member.id = :memberId AND c.clientState.id IN (1, 2, 3) AND c.clientState.id NOT IN (4, 5, 6) AND c.product.code = :productCode")
    long countClientsByMemberAndStateIn(
            Long memberId, 
            String productCode);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.member.id = :memberId AND c.clientState.id = :stateId AND c.product.code = :productCode AND c.clientState.id NOT IN :excludedStates")
    long countClientsByMemberAndStateExcluding(
            Long memberId, 
            Long stateId, 
            String productCode, 
            List<Long> excludedStates);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.member.id = :memberId AND c.clientState.id = :stateId AND c.product.code = :productCode")
    long countClientsByMemberAndState(
            Long memberId, 
            Long stateId, 
            String productCode);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.clientState.id = :stateId AND c.product.code = :productCode AND c.memberId != 6")
    long countAssignedClientsByState(
            Long stateId, 
            String productCode);

    @Query("SELECT COUNT(c) FROM Client c WHERE c.product.code = :productCode AND c.clientState.id IN :stateIds AND c.clientState.id NOT IN :excludedStates")
    long countClientsByProductExcluding(
            String productCode, 
            List<Long> stateIds, 
            List<Long> excludedStates);
}
