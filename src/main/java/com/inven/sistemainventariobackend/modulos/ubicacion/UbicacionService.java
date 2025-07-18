package com.inven.sistemainventariobackend.modulos.ubicacion;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Ubicacion> obtenerTodas() {
        return ubicacionRepository.findAll();
    }

    public Ubicacion guardar(Ubicacion ubicacion) {
        ubicacion.setFechaRegistro(LocalDate.now());
        return ubicacionRepository.save(ubicacion);
    }

    public void eliminar(Long id) {
        ubicacionRepository.deleteById(id);
    }

    public Ubicacion actualizar(Long id, Ubicacion nuevaUbicacion) {
        Ubicacion actual = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));

        actual.setTipo(nuevaUbicacion.getTipo());
        actual.setSubTipo(nuevaUbicacion.getSubTipo());
        actual.setDescripcion(nuevaUbicacion.getDescripcion());
        actual.setEstado(nuevaUbicacion.getEstado());

        return ubicacionRepository.save(actual);
    }
}

