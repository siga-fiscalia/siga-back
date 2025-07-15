package com.inven.sistemainventariobackend.modulos.centrocosto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "centros_costo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Centro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String descripcion;
    private String direccion;
    private String referencia;

    private String pais;
    private String departamento;
    private String provincia;
    private String distrito;

    @Column(name = "telefono_anexo")
    private String telefonoAnexo;

    @Column(name = "fecha_registro")
    private String fechaRegistro;

    private String ambito;
}

