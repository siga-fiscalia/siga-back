package com.inven.sistemainventariobackend;

import com.inven.sistemainventariobackend.security.entity.Admin;
import com.inven.sistemainventariobackend.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class SistemaInventarioBackendApplication {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SistemaInventarioBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {

                Admin defaultAdmin = new Admin();
                defaultAdmin.setUsername("admin");
                defaultAdmin.setPassword(passwordEncoder.encode("admin123"));
                defaultAdmin.setEmail("admin@sistema.com");
                defaultAdmin.setActivo(true);
                defaultAdmin.setRol(com.inven.sistemainventariobackend.security.enums.Rol.ADMIN);
                defaultAdmin.setFechaCreacion(java.time.LocalDateTime.now());
                defaultAdmin.setPrimerLogin(true);
                defaultAdmin.setNombres("Administrador");
                defaultAdmin.setApellidos("Del Sistema");
                defaultAdmin.setNivelAcceso("SUPER_ADMIN");
                defaultAdmin.setPuedeCrearUsuarios(true);
                defaultAdmin.setPuedeModificarSistema(true);

                usuarioRepository.save(defaultAdmin);

                System.out.println("Administrador creado exitosamente:");
                System.out.println("Username: admin");
                System.out.println("Password: admin123");
                System.out.println("Email: admin@sistema.com");
            } else {
                System.out.println("El administrador ya existe, omitiendo creación.");
            }
        };
    }
}