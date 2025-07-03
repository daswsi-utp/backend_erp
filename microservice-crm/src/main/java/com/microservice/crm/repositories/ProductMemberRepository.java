package com.microservice.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.crm.entities.ProductMember;

public interface ProductMemberRepository extends JpaRepository<ProductMember, Long> {
	List<ProductMember> findByMemberId(Long memberId);
    List<ProductMember> findByProductId(Long productId);
}