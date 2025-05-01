package com.microservice.auth.services;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.dto.ChangePasswordRequest;
import com.microservice.auth.entities.Role;
import com.microservice.auth.entities.User;
import com.microservice.auth.repositories.RoleRepository;
import com.microservice.auth.repositories.UserRepository;
import com.microservice.auth.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

   
    @Override
    public AuthResponse login(LoginRequest request) {
        System.out.println("Buscando usuario con: " + request.getUsername());
        
        // Verifica qué usuarios existen
        System.out.println("Usuarios en BD:");
        userRepository.findAll().forEach(u -> 
            System.out.println("ID: " + u.getId() + ", DNI: " + u.getDni() + ", Email: " + u.getEmail()));
        
        Optional<User> userOpt = userRepository.findByDniOrEmail(request.getUsername());
        System.out.println("Usuario encontrado?: " + userOpt.isPresent());
        
        User user = userOpt.orElseThrow(() -> {
            System.out.println("No se encontró usuario con: " + request.getUsername());
            return new RuntimeException("Usuario no encontrado");
        });

        System.out.println("Usuario encontrado: " + user.getEmail());
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.out.println("Contraseña no coincide");
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new AuthResponse(jwtService.generateToken(user));
    }

    @Override
    public User register(RegisterRequest request) {
        Role role = roleRepository.findByName(request.getRoleName())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        User user = User.builder()
            .email(request.getEmail())
            .dni(request.getDni())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(Collections.singleton(role))
            .build();

        return userRepository.save(user);
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
}