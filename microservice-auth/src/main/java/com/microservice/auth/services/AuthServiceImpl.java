package com.microservice.auth.services;

import com.microservice.auth.config.JwtService;
import com.microservice.auth.dto.*;
import com.microservice.auth.entities.BlacklistedToken;
import com.microservice.auth.entities.RefreshToken;
import com.microservice.auth.entities.User;
import com.microservice.auth.repositories.BlacklistedTokenRepository;
import com.microservice.auth.repositories.RefreshTokenRepository;
import com.microservice.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    
    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByDniOrEmail(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        if (!user.isActive()) {
            throw new DisabledException("Cuenta no activada");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        // Generar el accessToken y el refreshToken
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // Guardar el refreshToken en la base de datos con su fecha de expiración
        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setUserId(user.getId());
        newRefreshToken.setRefreshToken(refreshToken);
        newRefreshToken.setExpireAt(new Date(System.currentTimeMillis() + jwtService.getRefreshTokenExpiration()));  // Usamos getRefreshTokenExpiration()
        refreshTokenRepository.save(newRefreshToken);  // Guardamos el refreshToken

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .dni(user.getDni())
                .id(user.getId())
                .build();
    }




    @Override
    public User activateAccount(ActivateAccountRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        User newUser = User.builder()
                .email(request.getEmail())
                .dni(request.getDni())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, String token) {
        String userEmail = jwtService.extractUsername(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }
        
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String userEmail = jwtService.extractUsername(refreshToken);
        
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        RefreshToken storedRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token no encontrado"));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new RuntimeException("Refresh token inválido");
        }

        String newAccessToken = jwtService.generateAccessToken(user);
        return TokenRefreshResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)  // Retornamos el mismo refreshToken
                .build();
    }


    @Override
    public void logout(String token) {
        tokenBlacklistService.blacklistToken(token);        
        refreshTokenRepository.deleteByRefreshToken(token);  // Usamos el método para eliminar el refreshToken
    }

}