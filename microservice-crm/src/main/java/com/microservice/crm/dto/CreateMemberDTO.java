package com.microservice.crm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberDTO {
    private Long employeeId;
    private String crmRole;
    private Long teamId;
    private Integer status;
}
