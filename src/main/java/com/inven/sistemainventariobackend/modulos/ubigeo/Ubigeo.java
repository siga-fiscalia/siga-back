package com.inven.sistemainventariobackend.modulos.ubigeo;

import java.util.List;

public class Ubigeo {
    private String departamento;
    private List<Provincia> provincias;

    // Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }
}
