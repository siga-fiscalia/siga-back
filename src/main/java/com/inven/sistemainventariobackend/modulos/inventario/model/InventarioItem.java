package com.inven.sistemainventariobackend.modulos.inventario.model;

import com.inven.sistemainventariobackend.modulos.inventario.model.Inventario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InventarioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoPatrimonial;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private String unidad;
    private Double valor;
    private String estado;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    // Getters y Setters
}

