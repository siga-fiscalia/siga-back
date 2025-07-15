package com.inven.sistemainventariobackend.modulos.actividad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface actividadRepository extends JpaRepository<actividad, Long> {
    List<actividad> findTop5ByOrderByFechaHoraDesc(); // Últimas 5 actividades

}
