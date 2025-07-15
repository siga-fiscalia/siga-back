package com.inven.sistemainventariobackend.modulos.inventario.repository;

import com.inven.sistemainventariobackend.modulos.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {}
