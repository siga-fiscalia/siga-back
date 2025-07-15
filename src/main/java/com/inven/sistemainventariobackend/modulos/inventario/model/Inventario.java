package com.inven.sistemainventariobackend.modulos.inventario.model;

import com.inven.sistemainventariobackend.modulos.inventario.enums.TipoInventario;
import com.inven.sistemainventariobackend.modulos.inventario.enums.TipoRegistro;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responsable;
    private String oficina;

    @Enumerated(EnumType.STRING)
    private TipoInventario tipoInventario;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;

    private LocalDate fecha;

    // Getters y Setters
}

