package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtletaDTO {

    private Integer idAtleta;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaDeNacimiento;
    private String sexo;
    private String correo;
    private BigDecimal peso;
    private BigDecimal estatura;
    private Integer equipoId;

}