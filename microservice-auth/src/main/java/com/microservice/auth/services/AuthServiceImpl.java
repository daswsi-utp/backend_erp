package com.microservice.auth.services;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.entities.Role;
import com.microservice.auth.entities.User;
import com.microservice.auth.repositories.RoleRepository;
import com.microservice.auth.repositories.UserRepository;
import com.microservice.auth.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public User register(RegisterRequest request) {
        Role role = roleRepository.findByName(request.getRoleName())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(Collections.singleton(role))
            .build();

        return userRepository.save(user);
    }
}