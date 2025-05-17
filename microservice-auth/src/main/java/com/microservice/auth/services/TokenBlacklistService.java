package com.microservice.auth.services;

import com.microservice.auth.entities.BlacklistedToken;
import com.microservice.auth.repositories.BlacklistedTokenRepository;
import com.microservice.auth.config.JwtService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenBlacklistService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;
    private final JwtService jwtService;

    // Constructor que recibe el repositorio y el servicio JwtService
    public TokenBlacklistService(BlacklistedTokenRepository blacklistedTokenRepository, JwtService jwtService) {
        this.blacklistedTokenRepository = blacklistedTokenRepository;
        this.jwtService = jwtService;
    }

    // Método para agregar un token a la lista negra (base de datos)
    public void blacklistToken(String token) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpireAt(new Date(System.currentTimeMillis() + jwtService.getRefreshTokenExpiration()));  // Establecer la expiración
        blacklistedTokenRepository.save(blacklistedToken);  // Guardar el token en la base de datos
    }

    // Método para verificar si un token está en la lista negra
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token).isPresent();  // Verifica si el token está en la base de datos
    }
}