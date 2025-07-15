package com.inven.sistemainventariobackend.modulos.bienes;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bienes")
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Null
    private String item;
    @Null
    private String codigoPatrimonial;
    @Null
    private String descripcion;
    @Null
    private String sede;
    @Null
    private String centroCosto;
    @Null
    private String ubicacionFisica;
    @Null
    private String responsable;
    @Null
    private String usuarioFinal;
    @Null
    private String numeroSerie;
    @Null
    private String estadoConservacion;
    @Null
    private String marca;
    @Null
    private Boolean estadoUso;
    @Null
    private String modelo;
    @Null
    private String medidas;
    @Null
    private String caracteristicas;
    @Null
    private String observaciones;
    @Null
    private String correlativo;
    @Null
    private String codigoBarraAnterior;
    @Null
    private String paisProcedencia;
    @Null
    private String estado;
    @Null
    private String tipoPatrimonio;
    @Null
    private String categoria;
    @Null
    private Boolean sbn;
    @Null
    private Boolean activoDepreciable;
    @Null
    private Boolean salida;
    @Null
    private String tipoIngreso;
    @Null
    private LocalDate fechaIngreso;
    @Null
    private Boolean verificacionFisica;
    @Null
    private Boolean etiquetado;
    @Null
    private String tipoDocumentoIngreso; // "OC" o "NEA"
    @Null
    private String numeroOC;
    @Null
    private LocalDate fechaOC;
    @Null
    private String proveedor;
    @Null
    private BigDecimal valorCompra;
    @Null
    private LocalDate fechaGarantia;
    @Null
    private String numeroContrato;
    @Null
    private String tipoDocumentoAlta;
    @Null
    private String numeroDocumentoAlta;
    @Null
    private LocalDate fechaAlta;
    @Null
    private String almacen;
    @Null
    private String cuentaContable;
}

