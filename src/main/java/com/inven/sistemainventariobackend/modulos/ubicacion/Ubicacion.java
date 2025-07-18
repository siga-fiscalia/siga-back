package com.inven.sistemainventariobackend.modulos.ubicacion;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El tipo es obligatorio")
    @Digits(integer = 10, fraction = 0, message = "El tipo debe ser un número entero")
    private Integer tipo;

    @NotNull(message = "El subTipo es obligatorio")
    @Digits(integer = 10, fraction = 0, message = "El subTipo debe ser un número entero")
    private Integer subTipo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private String estado;

    private LocalDate fechaRegistro;
}

