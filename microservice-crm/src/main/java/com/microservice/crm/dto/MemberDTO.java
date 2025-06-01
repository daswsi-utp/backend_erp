package com.microservice.crm.dto;

import com.microservice.crm.entities.Status;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long id;
    private Long employeeId;
    private String fullName;
    private String crmRole;
    private Long teamId;
    private Integer status;
    private String phone;
    private String address;
    private String teamName;
    private String createdAt;
}