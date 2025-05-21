package com.microservice.auth.repositories;

import com.microservice.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.dni = :identifier OR u.email = :identifier")
	Optional<User> findByDniOrEmail(@Param("identifier") String identifier);
    
    Optional<User> findByEmail(String email);
    Optional<User> findByDni(String dni);
    boolean existsByEmail(String email); 
}