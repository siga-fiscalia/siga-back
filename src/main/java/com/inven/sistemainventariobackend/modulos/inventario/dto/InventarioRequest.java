package com.inven.sistemainventariobackend.modulos.inventario.dto;

import com.inven.sistemainventariobackend.modulos.inventario.enums.TipoInventario;
import com.inven.sistemainventariobackend.modulos.inventario.enums.TipoRegistro;

import java.time.LocalDate;

public class InventarioRequest {
    public String responsable;
    public String oficina;
    public TipoInventario tipoInventario;
    public TipoRegistro tipoRegistro;
    public LocalDate fecha;
}

