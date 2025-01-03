package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Integer idEvento;
    private String nombre;
    private Date fechaInicioInscripciones;
    private Date fechaFinInscripciones;
    private Date fechaInicioEvento;
    private Date fechaFinEvento;
    private String modalidades;
    private String categoria;
    private BigDecimal costo;
    private String requisitos;
    private String reglas;
    private Integer tipoEventoId;
    private Integer entrenadorId;
    private Integer administradorId;
    private String estado;

}