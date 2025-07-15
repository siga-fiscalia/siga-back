package com.inven.sistemainventariobackend.modulos.dashboard.dto;

import java.time.LocalDateTime;

public class ActividadDTO {
    private String descripcion;
    private String modulo;
    private String usuario;
    private LocalDateTime fechaHora;

    public ActividadDTO(String descripcion, String modulo, String usuario, LocalDateTime fechaHora) {
        this.descripcion = descripcion;
        this.modulo = modulo;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getModulo() {
        return modulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}

