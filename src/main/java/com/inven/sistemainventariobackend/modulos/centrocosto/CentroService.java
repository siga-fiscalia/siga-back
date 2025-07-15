package com.inven.sistemainventariobackend.modulos.centrocosto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroService {

    @Autowired
    private CentroRepository centroRepository;

    public Centro crearCentro(Centro centro) {
        return centroRepository.save(centro);
    }

    public List<Centro> listarCentros() {
        return centroRepository.findAll();
    }

    public Centro obtenerPorId(Long id) {
        return centroRepository.findById(id).orElse(null);
    }

    public void eliminarCentro(Long id) {
        centroRepository.deleteById(id);
    }
}

