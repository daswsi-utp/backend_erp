package com.microservice.auth.services;

import com.microservice.auth.dto.*;
import com.microservice.auth.entities.User;

public interface IAuthService {
    AuthResponse login(LoginRequest request);
    User activateAccount(ActivateAccountRequest request);
    void changePassword(ChangePasswordRequest request, String token);
    TokenRefreshResponse refreshToken(TokenRefreshRequest request);
    void logout(String token);
}