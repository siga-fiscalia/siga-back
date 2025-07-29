package com.inven.sistemainventariobackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:3000")); // Agregué puerto 3000 por si usas React
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "X-Requested-With"));
            configuration.setAllowCredentials(true);
            configuration.setExposedHeaders(Arrays.asList("Authorization"));
            return configuration;
        }));

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas de autenticación
                        .requestMatchers("/api/v1/auth/login", "/api/v1/auth/validate").permitAll()

                        // Registro solo para admins autenticados
                        .requestMatchers("/api/v1/auth/register").hasRole("ADMIN")

                        // Rutas de documentación (Swagger)
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**").permitAll()

                        // Rutas específicas por rol
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/personal/**").hasAnyRole("ADMIN", "PERSONAL")

                        // Rutas del dashboard - accesibles para usuarios autenticados
                        .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "PERSONAL")

                        // Rutas de módulos - accesibles según permisos
                        .requestMatchers("/api/bienes/**").hasAnyRole("ADMIN", "PERSONAL")
                        .requestMatchers("/api/centros/**").hasAnyRole("ADMIN", "PERSONAL")
                        .requestMatchers("/api/ubicaciones/**").hasAnyRole("ADMIN", "PERSONAL")
                        .requestMatchers("/inventario/**").hasAnyRole("ADMIN", "PERSONAL")
                        .requestMatchers("/api/ubigeo/**").hasAnyRole("ADMIN", "PERSONAL")

                        // Solo admins pueden gestionar personal (crear, modificar, eliminar)
                        .requestMatchers("/api/personal").hasAnyRole("ADMIN", "PERSONAL") // GET - listar
                        .requestMatchers("/api/personal/**").hasRole("ADMIN") // POST, PUT, DELETE

                        // Todas las demás rutas requieren autenticación
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}