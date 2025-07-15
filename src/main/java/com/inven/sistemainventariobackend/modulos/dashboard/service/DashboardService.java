package com.inven.sistemainventariobackend.modulos.dashboard.service;

import com.inven.sistemainventariobackend.modulos.actividad.actividadRepository;
import com.inven.sistemainventariobackend.modulos.bienes.BienRepository;
import com.inven.sistemainventariobackend.modulos.dashboard.dto.DashboardKpiResponse;
import com.inven.sistemainventariobackend.modulos.dashboard.dto.ActividadDTO;
import com.inven.sistemainventariobackend.modulos.centrocosto.CentroRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final BienRepository bienRepository;
    private final CentroRepository centroRepository;
    private final actividadRepository actividadRepository;

    public DashboardKpiResponse obtenerKPIsYActividades() {
        long totalBienes = bienRepository.count();
        long totalCentrosCosto = centroRepository.count();


        List<ActividadDTO> ultimasActividades = actividadRepository.findTop5ByOrderByFechaHoraDesc()
                .stream()
                .map(a -> new ActividadDTO(
                        a.getDescripcion(),
                        a.getModulo(),
                        a.getUsuario(),
                        a.getFechaHora()
                ))
                .collect(Collectors.toList());

        return new DashboardKpiResponse(totalBienes, totalCentrosCosto, ultimasActividades);
    }
}

