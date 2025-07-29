package com.inven.sistemainventariobackend.modulos.personal;

import com.inven.sistemainventariobackend.modulos.personal.enums.Estado;
import com.inven.sistemainventariobackend.modulos.personal.enums.EstadoCivil;
import com.inven.sistemainventariobackend.modulos.personal.enums.Sexo;
import com.inven.sistemainventariobackend.modulos.personal.enums.TipoEmpleado;
import com.inven.sistemainventariobackend.security.entity.Usuario;
import com.inven.sistemainventariobackend.security.enums.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name = "personal")
@DiscriminatorValue("PERSONAL")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personal extends Usuario {

    private String codigo;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    private String nombres;

    @Column(length = 8, unique = true)
    private String dni;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private Double peso;
    private String ruc;

    @Column(name = "libreta_militar")
    private String libretaMilitar;

    private String pasaporte;

    @Column(name = "numero_ssp")
    private String numeroSSP;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_empleado")
    private TipoEmpleado tipoEmpleado;

    private Boolean destacado;
    private String escala;
    private String ejecutoria;

    private String profesion;

    @Column(name = "profesion_inei")
    private String profesionInei;

    @Column(name = "grado_instructivo")
    private String gradoInstructivo;

    @Column(name = "nro_colegiatura")
    private String nroColegiatura;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    // Campos de ubicación
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String interior;
    private String mz;
    private String lote;
    private Integer habitacion;

    // Campos de contacto
    private String telefono;
    private String telefax;

    @Column(name = "cuenta_deposito")
    private String cuentaDeposito;

    private Boolean autorizaciones;

    // Métodos de utilidad
    public String getNombreCompleto() {
        StringBuilder nombre = new StringBuilder();
        if (nombres != null) nombre.append(nombres);
        if (apellidoPaterno != null) nombre.append(" ").append(apellidoPaterno);
        if (apellidoMaterno != null) nombre.append(" ").append(apellidoMaterno);
        return nombre.toString().trim();
    }

    public boolean isActivoEnSistema() {
        return isEnabled() && Estado.ACTIVO.equals(estado);
    }
}