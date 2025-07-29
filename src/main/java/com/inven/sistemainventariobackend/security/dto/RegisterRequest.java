package com.inven.sistemainventariobackend.security.dto;

import com.inven.sistemainventariobackend.security.enums.Rol;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "El username es obligatorio")
    @Size(max = 50, message = "El username no puede exceder 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;

    // Campos comunes para ambos tipos de usuario
    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no pueden exceder 100 caracteres")
    private String nombres;

    // Campos específicos para Admin
    @Size(max = 100, message = "Los apellidos no pueden exceder 100 caracteres")
    private String apellidos; // Para Admin

    private String nivelAcceso; // Para Admin
    private Boolean puedeCrearUsuarios; // Para Admin
    private Boolean puedeModificarSistema; // Para Admin

    // Campos específicos para Personal
    @Size(max = 100, message = "El apellido paterno no puede exceder 100 caracteres")
    private String apellidoPaterno; // Para Personal

    @Size(max = 100, message = "El apellido materno no puede exceder 100 caracteres")
    private String apellidoMaterno; // Para Personal

    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    private String dni; // Para Personal

    @Size(max = 9, message = "El teléfono no puede exceder 9 caracteres")
    private String telefono; // Para Personal

    private LocalDate fechaNacimiento; // Para Personal
    private LocalDate fechaIngreso; // Para Personal
    private String profesion; // Para Personal
    private String departamento; // Para Personal
    private String provincia; // Para Personal
    private String distrito; // Para Personal
    private String direccion; // Para Personal
}