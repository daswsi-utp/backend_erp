package com.microservice.auth.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private UUID userId;
    private String currentPassword; 
    private String newPassword;   
}