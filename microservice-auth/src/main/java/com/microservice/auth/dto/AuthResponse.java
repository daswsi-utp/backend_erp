package com.microservice.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	

    private String accessToken;
    private String refreshToken;
    private String email;
    private String dni;
    private Long id;
}