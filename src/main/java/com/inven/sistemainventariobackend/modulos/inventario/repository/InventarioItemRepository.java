package com.inven.sistemainventariobackend.modulos.inventario.repository;

import com.inven.sistemainventariobackend.modulos.inventario.model.InventarioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioItemRepository extends JpaRepository<InventarioItem, Long> {
    List<InventarioItem> findByInventario_Id(Long inventarioId);
}

