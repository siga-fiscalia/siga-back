package com.inven.sistemainventariobackend.security.service;

import com.inven.sistemainventariobackend.security.*;
import com.inven.sistemainventariobackend.security.dto.RegisterRequest;
import com.inven.sistemainventariobackend.security.entity.Admin;
import com.inven.sistemainventariobackend.security.entity.Usuario;
import com.inven.sistemainventariobackend.security.enums.Rol;
import com.inven.sistemainventariobackend.security.repository.UsuarioRepository;
import com.inven.sistemainventariobackend.modulos.personal.Personal;
import com.inven.sistemainventariobackend.modulos.personal.enums.Estado;
import com.inven.sistemainventariobackend.modulos.personal.enums.TipoEmpleado;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse login(LoginRequest request) {
        // Autenticar usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Buscar usuario en base de datos
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Verificar que el usuario esté activo
        if (!usuario.isEnabled()) {
            throw new RuntimeException("Usuario inactivo");
        }

        // Actualizar último acceso
        usuario.actualizarUltimoAcceso();
        usuarioRepository.save(usuario);

        // Crear UserDetails para el JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // Generar token
        String token = jwtService.getToken(userDetails, usuario);

        return TokenResponse.builder()
                .token(token)
                .firstLogin(usuario.getPrimerLogin())
                .build();
    }

    public TokenResponse refreshToken(String token) {
        String username = jwtService.getUsernameFromToken(token);

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!usuario.isEnabled()) {
            throw new RuntimeException("Usuario inactivo");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(token, userDetails)) {
            String newToken = jwtService.getToken(userDetails, usuario);

            return TokenResponse.builder()
                    .token(newToken)
                    .firstLogin(false)
                    .build();
        }

        throw new RuntimeException("Token inválido");
    }

    public TokenResponse register(RegisterRequest data) {
        // Validaciones de duplicados
        if (usuarioRepository.existsByUsername(data.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso.");
        }
        if (usuarioRepository.existsByEmail(data.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        // Validación específica para Personal (DNI)
        if (data.getRol() == Rol.PERSONAL && data.getDni() != null) {
            if (usuarioRepository.findAll().stream()
                    .anyMatch(u -> u instanceof Personal &&
                            ((Personal) u).getDni() != null &&
                            ((Personal) u).getDni().equals(data.getDni()))) {
                throw new IllegalArgumentException("El DNI ya está en uso.");
            }
        }

        Usuario usuario = crearUsuarioSegunRol(data);
        usuarioRepository.save(usuario);

        // Crear UserDetails para el JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());

        // Generar token
        String token = jwtService.getToken(userDetails, usuario);

        return TokenResponse.builder()
                .token(token)
                .firstLogin(true)
                .build();
    }

    private Usuario crearUsuarioSegunRol(RegisterRequest data) {
        if (data.getRol() == Rol.ADMIN) {
            Admin admin = new Admin();
            admin.setUsername(data.getUsername());
            admin.setPassword(passwordEncoder.encode(data.getPassword()));
            admin.setEmail(data.getEmail());
            admin.setActivo(true);
            admin.setRol(Rol.ADMIN);
            admin.setFechaCreacion(java.time.LocalDateTime.now());
            admin.setPrimerLogin(true);
            admin.setNombres(data.getNombres());
            admin.setApellidos(data.getApellidos() != null ? data.getApellidos() : "");
            admin.setNivelAcceso(data.getNivelAcceso() != null ? data.getNivelAcceso() : "ADMIN");
            admin.setPuedeCrearUsuarios(data.getPuedeCrearUsuarios() != null ? data.getPuedeCrearUsuarios() : true);
            admin.setPuedeModificarSistema(data.getPuedeModificarSistema() != null ? data.getPuedeModificarSistema() : false);
            return admin;
        } else if (data.getRol() == Rol.PERSONAL) {
            // Validar campos obligatorios para Personal
            if (data.getDni() == null || data.getDni().trim().isEmpty()) {
                throw new IllegalArgumentException("El DNI es obligatorio para el personal.");
            }

            Personal personal = new Personal();
            personal.setUsername(data.getUsername());
            personal.setPassword(passwordEncoder.encode(data.getPassword()));
            personal.setEmail(data.getEmail());
            personal.setActivo(true);
            personal.setRol(Rol.PERSONAL);
            personal.setFechaCreacion(java.time.LocalDateTime.now());
            personal.setPrimerLogin(true);
            personal.setCodigo(generarCodigoPersonal());
            personal.setNombres(data.getNombres());
            personal.setApellidoPaterno(data.getApellidoPaterno());
            personal.setApellidoMaterno(data.getApellidoMaterno());
            personal.setDni(data.getDni());
            personal.setTelefono(data.getTelefono());
            personal.setFechaNacimiento(data.getFechaNacimiento());
            personal.setFechaIngreso(data.getFechaIngreso() != null ? data.getFechaIngreso() : java.time.LocalDate.now());
            personal.setTipoEmpleado(TipoEmpleado.CONTRATADO);
            personal.setEstado(Estado.ACTIVO);
            personal.setProfesion(data.getProfesion());
            personal.setDepartamento(data.getDepartamento());
            personal.setProvincia(data.getProvincia());
            personal.setDistrito(data.getDistrito());
            personal.setDireccion(data.getDireccion());
            return personal;
        } else {
            throw new IllegalArgumentException("Rol no válido: " + data.getRol());
        }
    }

    private String generarCodigoPersonal() {
        // Contar cuántos usuarios Personal hay
        long totalPersonal = usuarioRepository.findAll().stream()
                .filter(u -> u instanceof Personal)
                .count();
        return String.format("%03d", totalPersonal + 1); // 001, 002, 003...
    }
}