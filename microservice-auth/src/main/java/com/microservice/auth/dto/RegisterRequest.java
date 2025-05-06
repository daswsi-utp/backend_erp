package com.microservice.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String dni;
    private String roleName; 
}