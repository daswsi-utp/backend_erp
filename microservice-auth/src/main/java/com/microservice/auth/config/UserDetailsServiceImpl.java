package com.microservice.auth.config;

import com.microservice.auth.entities.User;
import com.microservice.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByDniOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        if (!user.isActive()) {
            throw new UsernameNotFoundException("Cuenta no activada");
        }
        return user;
    }
}