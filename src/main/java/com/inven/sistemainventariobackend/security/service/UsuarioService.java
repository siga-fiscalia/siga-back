package com.inven.sistemainventariobackend.security.service;

import com.inven.sistemainventariobackend.security.entity.Usuario;
import com.inven.sistemainventariobackend.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    public Usuario toggleEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setActivo(!usuario.getActivo());
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public void cambiarPassword(String passwordActual, String passwordNuevo) {
        Usuario usuario = obtenerUsuarioActual();

        // Verificar contraseña actual
        if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        // Validar nueva contraseña
        if (passwordNuevo == null || passwordNuevo.length() < 6) {
            throw new RuntimeException("La nueva contraseña debe tener al menos 6 caracteres");
        }

        // Actualizar contraseña
        usuario.setPassword(passwordEncoder.encode(passwordNuevo));
        usuario.setPrimerLogin(false);
        usuarioRepository.save(usuario);
    }

    public boolean existeUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public long contarUsuariosActivos() {
        return usuarioRepository.findByActivoTrue().size();
    }

    public long contarAdmins() {
        return usuarioRepository.countActiveUsersByRole(
                com.inven.sistemainventariobackend.security.enums.Rol.ADMIN
        );
    }

    public long contarPersonal() {
        return usuarioRepository.countActiveUsersByRole(
                com.inven.sistemainventariobackend.security.enums.Rol.PERSONAL
        );
    }
}