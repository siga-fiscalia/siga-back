package com.inven.sistemainventariobackend.security;

import com.inven.sistemainventariobackend.security.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails userDetails, Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", usuario.getId());
        claims.put("role", usuario.getRol().name());
        claims.put("username", usuario.getUsername());
        claims.put("email", usuario.getEmail());
        claims.put("activo", usuario.getActivo());

        // Agregar información específica según el tipo de usuario
        if (usuario instanceof com.inven.sistemainventariobackend.security.entity.Admin) {
            var admin = (com.inven.sistemainventariobackend.security.entity.Admin) usuario;
            claims.put("tipo", "ADMIN");
            claims.put("nombreCompleto", admin.getNombreCompleto());
        } else if (usuario instanceof com.inven.sistemainventariobackend.modulos.personal.Personal) {
            var personal = (com.inven.sistemainventariobackend.modulos.personal.Personal) usuario;
            claims.put("tipo", "PERSONAL");
            claims.put("nombreCompleto", personal.getNombreCompleto());
            claims.put("codigo", personal.getCodigo());
            claims.put("dni", personal.getDni());
        }

        return generateToken(claims, userDetails.getUsername());
    }

    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de validez
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    // Métodos adicionales para obtener información del token
    public String getUserRoleFromToken(String token) {
        return getClaim(token, claims -> claims.get("role", String.class));
    }

    public Long getUserIdFromToken(String token) {
        return getClaim(token, claims -> claims.get("userId", Long.class));
    }

    public String getUserTypeFromToken(String token) {
        return getClaim(token, claims -> claims.get("tipo", String.class));
    }
}