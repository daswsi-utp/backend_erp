package com.microservice.crm.services;

import com.microservice.crm.entities.Client;
import com.microservice.crm.entities.Member;
import com.microservice.crm.repositories.ClientRepository;
import com.microservice.crm.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LeadsService {

    private final ClientRepository clientRepository;
    private final MemberRepository memberRepository;

    public LeadsService(ClientRepository clientRepository, MemberRepository memberRepository) {
        this.clientRepository = clientRepository;
        this.memberRepository = memberRepository;
    }

    public Map<String, Object> getMembersAndBags(String productCode) {
        Long stateId = 1L;  
        Long memberIdFree = 6L; 
        Set<Long> stateIds = new HashSet<>(List.of(1L, 2L, 3L));  

        List<Member> members = memberRepository.findByCrmRoleAndStatus("ASESOR_CRM", 1);

        List<Map<String, Object>> memberData = members.stream()
            .map(member -> {
            	long totalLeads = clientRepository.countClientsByMemberAndStateIn(
                        member.getId(), productCode); 

                long totalNc = clientRepository.countClientsByMemberAndState(
                    member.getId(), stateId, productCode 
                );

                long totalClients = clientRepository.countClientsByMemberAndStateExcluding(
                    member.getId(), stateId, productCode, List.of(4L, 5L, 6L) 
                );

                Map<String, Object> memberMap = new HashMap<>();
                memberMap.put("user_id", member.getId());
                memberMap.put("full_name", member.getFullName());
                memberMap.put("total_leads", totalLeads); 
                memberMap.put("total_nc", totalNc);     
                memberMap.put("total_clients", totalClients); 
                return memberMap;
            })
            .collect(Collectors.toList());

        long clientsBag = clientRepository.countClientsByMemberAndState(
            memberIdFree, stateId, productCode);  

        return Map.of(
            "members", memberData,  
            "clients_bag", clientsBag  
        );
    }
}
