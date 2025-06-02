package com.microservice.rrhh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	
	private Long employeeId;
    private String crmRole;
    private Long teamId;
    private Integer status;
    
}
