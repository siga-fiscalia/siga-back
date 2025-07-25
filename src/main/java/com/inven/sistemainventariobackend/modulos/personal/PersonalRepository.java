package com.inven.sistemainventariobackend.modulos.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    boolean existsByDni(String dni);
}

