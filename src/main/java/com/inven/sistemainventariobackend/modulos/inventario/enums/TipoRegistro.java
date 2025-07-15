package com.inven.sistemainventariobackend.modulos.inventario.enums;

public enum TipoRegistro {

    INSTITUCIONAL("Institucional"),
    NO_INST_CEDIDOS_EN_USO("No institucional - Bienes cedidos en uso"),
    NO_INST_EN_COMODATO("No institucional - Bienes en Comodato"),
    NO_INST_EN_AFECTACION_EN_USO("No institucional - Bienes en Afectación en Uso"),
    NO_INST_ALQUILADOS("No institucional - Bienes Alquilados"),
    NO_INST_INCAUTADOS("No institucionales - Bienes incautados");

    private final String label;

    TipoRegistro(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

