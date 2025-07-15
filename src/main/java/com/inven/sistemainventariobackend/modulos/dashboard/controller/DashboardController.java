package com.inven.sistemainventariobackend.modulos.dashboard.controller;

import com.inven.sistemainventariobackend.modulos.dashboard.dto.DashboardKpiResponse;
import com.inven.sistemainventariobackend.modulos.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/kpis")
    public DashboardKpiResponse obtenerKpisDashboard() {
        return dashboardService.obtenerKPIsYActividades();
    }
}

