package com.microservice.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    @jakarta.validation.constraints.NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username; 
    
    @jakarta.validation.constraints.NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}