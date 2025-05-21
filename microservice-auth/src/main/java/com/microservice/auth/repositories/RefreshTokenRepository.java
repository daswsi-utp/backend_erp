package com.microservice.auth.repositories;

import com.microservice.auth.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);  // Buscar por refreshToken
    void deleteByRefreshToken(String refreshToken);  // Eliminar el refreshToken
    void deleteByUserId(Long userId);  // Eliminar todos los refreshTokens de un usuario
}
