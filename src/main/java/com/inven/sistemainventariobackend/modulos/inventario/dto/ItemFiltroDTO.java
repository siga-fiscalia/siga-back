package com.inven.sistemainventariobackend.modulos.inventario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFiltroDTO {
    private String descripcion;
    private String estado;
    private Long tipoInventarioId;
}

