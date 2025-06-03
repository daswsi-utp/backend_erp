package com.microservice.crm.repositories;

import com.microservice.crm.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByCrmRole(String crmRole);
    List<Member> findByTeamId(Long teamId);
    List<Member> findByCrmRoleAndStatusCode(String crmRole, int statusCode);
    List<Member> findByStatusCode(int statusCode);

}
