package com.microservice.crm.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String whatsapp;
    private String country;
    private String countryCode;
    private String city;
    private String address;
    private String companyName;
    private String jobTitle;
    private Long clientStateId;
    private String clientStateName;
    private LocalDate birthDate;
    private String notes;
    private Long memberId;
    private String memberName; 
    private String productName; 
    private String productCode;
    private Long productId;
    private String reasonName;
    private Long arrivalMeanId;
    private String arrivalMeanName;
}
