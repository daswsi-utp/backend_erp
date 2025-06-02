package com.microservice.crm.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberDTO {
    private String fullName;
    private String crmRole;
    private Long teamId;
    private Integer status;
}
