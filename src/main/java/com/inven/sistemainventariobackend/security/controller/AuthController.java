package com.inven.sistemainventariobackend.security.controller;

import com.inven.sistemainventariobackend.security.*;
import com.inven.sistemainventariobackend.security.dto.RegisterRequest;
import com.inven.sistemainventariobackend.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')") // Solo admins pueden registrar usuarios
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        return ResponseEntity.ok(authService.refreshToken(jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // En implementaciones más avanzadas, aquí podrías invalidar el token
        return ResponseEntity.ok("Logout exitoso");
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.replace("Bearer ", "");
            // Usar directamente el JwtService para validar
            String username = jwtService.getUsernameFromToken(jwt);
            return ResponseEntity.ok(username != null && !username.isEmpty());
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}