package com.inven.sistemainventariobackend.modulos.personal;

import com.inven.sistemainventariobackend.modulos.personal.enums.Estado;
import com.inven.sistemainventariobackend.modulos.personal.enums.EstadoCivil;
import com.inven.sistemainventariobackend.modulos.personal.enums.Sexo;
import com.inven.sistemainventariobackend.modulos.personal.enums.TipoEmpleado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;

    @Column(length = 8)
    private String dni;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private LocalDate fechaNacimiento;

    private Double peso;
    private String ruc;
    private String libretaMilitar;
    private String pasaporte;
    private String numeroSSP;

    private LocalDate fechaIngreso;

    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    private Boolean destacado;
    private String escala;
    private String ejecutoria;

    private String profesion;
    private String profesionInei;
    private String gradoInstructivo;
    private String nroColegiatura;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private Boolean activo;
    private String departamento;
    private String provincia;
    private String distrito;

    private String direccion;
    private String interior;
    private String mz;
    private String lote;

    private Integer habitacion; // Puedes cambiar a Enum si lo deseas

    private String email;
    private String telefono;
    private String telefax;
    private String usuario;
    private String cuentaDeposito;

    private Boolean autorizaciones;
}

