package com.inven.sistemainventariobackend.security.controller;

import com.inven.sistemainventariobackend.security.entity.Usuario;
import com.inven.sistemainventariobackend.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/activos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listarActivos() {
        return ResponseEntity.ok(usuarioService.listarActivos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/toggle-estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> toggleEstado(@PathVariable Long id) {
        Usuario usuario = usuarioService.toggleEstado(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/perfil")
    public ResponseEntity<Usuario> obtenerPerfil() {
        Usuario usuario = usuarioService.obtenerUsuarioActual();
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<String> cambiarPassword(
            @RequestBody CambiarPasswordRequest request) {
        usuarioService.cambiarPassword(request.getPasswordActual(), request.getPasswordNuevo());
        return ResponseEntity.ok("Contraseña actualizada exitosamente");
    }

    // DTO interno para cambio de contraseña
    public static class CambiarPasswordRequest {
        private String passwordActual;
        private String passwordNuevo;

        public String getPasswordActual() { return passwordActual; }
        public void setPasswordActual(String passwordActual) { this.passwordActual = passwordActual; }

        public String getPasswordNuevo() { return passwordNuevo; }
        public void setPasswordNuevo(String passwordNuevo) { this.passwordNuevo = passwordNuevo; }
    }
}