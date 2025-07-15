package com.inven.sistemainventariobackend.modulos.bienes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BienesService {

    private final BienRepository bienRepository;

    @Autowired
    public BienesService(BienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }

    public List<Bien> obtenerTodosLosBienes() {
        return bienRepository.findAll();
    }

    public Bien obtenerPorId(Long id) {
        Optional<Bien> bien = bienRepository.findById(id);
        return bien.orElse(null);
    }

    public Bien guardarBien(Bien bien) {
        return bienRepository.save(bien);
    }

    public void eliminarPorId(Long id) {
    }
}

