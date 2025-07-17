package com.microservice.rrhh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.rrhh.dto.MemberDTO;

@FeignClient(name = "msvc-crm", url = "104.248.55.218:8081/api/v1/crm/members")

public interface MemberClient {
	
	@PostMapping
	void createMember(@RequestBody MemberDTO memberDTO);
	
}
