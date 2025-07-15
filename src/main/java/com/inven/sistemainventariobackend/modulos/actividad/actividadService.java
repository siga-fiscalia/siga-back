package com.inven.sistemainventariobackend.modulos.actividad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class actividadService {

    private final actividadRepository actividadRepository;

    public List<actividad> obtenerUltimasActividades() {
        return actividadRepository.findTop5ByOrderByFechaHoraDesc();
    }

    public void registrarActividad(String descripcion, String tipo) {
        actividad actividad = com.inven.sistemainventariobackend.modulos.actividad.actividad.builder()
                .descripcion(descripcion)
                .modulo(tipo)
                .usuario("admin") // o el nombre del usuario real si lo tienes disponible
                .fechaHora(LocalDateTime.now())
                .build();

        actividadRepository.save(actividad);
    }
}
