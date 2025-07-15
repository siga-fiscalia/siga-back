package com.inven.sistemainventariobackend.modulos.inventario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateDTO {
    private Long id;
    private String descripcion;
    private String estado;
    private Long tipoInventarioId;
}
