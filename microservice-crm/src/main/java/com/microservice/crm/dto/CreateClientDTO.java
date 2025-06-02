package com.microservice.crm.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateClientDTO {

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
    private LocalDate birthDate;
    private String notes;
    private Long employeeId;
    private Long productId;
    private Long reasonId;
    private Long arrivalMeanId;
}
