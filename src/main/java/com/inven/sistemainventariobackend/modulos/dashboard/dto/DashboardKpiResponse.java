package com.inven.sistemainventariobackend.modulos.dashboard.dto;

import java.util.List;
import java.time.LocalDateTime;

public class DashboardKpiResponse {
    private long totalBienes;
    private long totalCentrosCosto;
    private List<ActividadDTO> ultimasActividades;

    public DashboardKpiResponse(long totalBienes, long totalCentrosCosto, List<ActividadDTO> ultimasActividades) {
        this.totalBienes = totalBienes;
        this.totalCentrosCosto = totalCentrosCosto;
        this.ultimasActividades = ultimasActividades;
    }

    public long getTotalBienes() {
        return totalBienes;
    }

    public void setTotalBienes(long totalBienes) {
        this.totalBienes = totalBienes;
    }

    public long getTotalCentrosCosto() {
        return totalCentrosCosto;
    }

    public void setTotalCentrosCosto(long totalCentrosCosto) {
        this.totalCentrosCosto = totalCentrosCosto;
    }

    public List<ActividadDTO> getUltimasActividades() {
        return ultimasActividades;
    }

    public void setUltimasActividades(List<ActividadDTO> ultimasActividades) {
        this.ultimasActividades = ultimasActividades;
    }
}


