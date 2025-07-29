package com.inven.sistemainventariobackend.security.repository;

import com.inven.sistemainventariobackend.security.entity.Usuario;
import com.inven.sistemainventariobackend.security.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<Usuario> findByRol(Rol rol);

    List<Usuario> findByActivoTrue();

    List<Usuario> findByActivoFalse();

    @Query("SELECT u FROM Usuario u WHERE u.activo = true AND u.rol = :rol")
    List<Usuario> findActiveUsersByRole(@Param("rol") Rol rol);

    @Query("SELECT u FROM Usuario u WHERE u.username LIKE %:search% OR u.email LIKE %:search%")
    List<Usuario> searchByUsernameOrEmail(@Param("search") String search);

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = :rol AND u.activo = true")
    Long countActiveUsersByRole(@Param("rol") Rol rol);
}