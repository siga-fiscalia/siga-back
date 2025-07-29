package com.inven.sistemainventariobackend.security.entity;

import com.inven.sistemainventariobackend.security.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Usuario {

    private String nombres;

    private String apellidos;

    @Column(name = "nivel_acceso")
    private String nivelAcceso; // Para futuras expansiones de permisos

    @Column(name = "puede_crear_usuarios")
    private Boolean puedeCrearUsuarios = true;

    @Column(name = "puede_modificar_sistema")
    private Boolean puedeModificarSistema = true;

    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }
}