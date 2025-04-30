package com.microservice.auth.services;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.entities.User;

public interface IAuthService {
    AuthResponse login(LoginRequest request);
    User register(RegisterRequest request);
}