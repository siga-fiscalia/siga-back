package com.inven.sistemainventariobackend.modulos.inventario.dto;

public class EnumOptionDTO {
    private String value;
    private String label;

    public EnumOptionDTO(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}

