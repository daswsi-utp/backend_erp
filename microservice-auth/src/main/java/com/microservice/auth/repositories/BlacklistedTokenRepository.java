package com.microservice.auth.repositories;

import com.microservice.auth.entities.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    Optional<BlacklistedToken> findByToken(String token);  
    void deleteByUserId(Long userId); 
}
