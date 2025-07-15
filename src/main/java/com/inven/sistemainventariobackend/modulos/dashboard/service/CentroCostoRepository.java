package com.inven.sistemainventariobackend.modulos.dashboard.service;

import com.inven.sistemainventariobackend.modulos.centrocosto.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroCostoRepository extends JpaRepository<Centro, Long> {
}
