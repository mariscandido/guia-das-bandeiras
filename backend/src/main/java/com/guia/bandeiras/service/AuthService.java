package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.AuthResponse;
import com.guia.bandeiras.dto.LoginRequest;
import com.guia.bandeiras.dto.RegisterRequest;
import com.guia.bandeiras.entity.User;
import com.guia.bandeiras.repository.UserRepository;
import com.guia.bandeiras.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    
    public AuthResponse login(LoginRequest request) {
        log.info("Login attempt for user: {}", request.getUsername());
        
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        if (!user.getEnabled()) {
            throw new RuntimeException("User account is disabled");
        }
        
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
        
        log.info("User {} logged in successfully", request.getUsername());
        
        return new AuthResponse(token, user.getUsername(), user.getRole(), user.getId());
    }
    
    public AuthResponse register(RegisterRequest request) {
        log.info("Registration attempt for user: {}", request.getUsername());
        
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole("USER");
        user.setPoints(0);
        user.setRank(1);
        user.setEnabled(true);
        
        userRepository.save(user);
        
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
        
        log.info("User {} registered successfully", request.getUsername());
        
        return new AuthResponse(token, user.getUsername(), user.getRole(), user.getId());
    }
}
