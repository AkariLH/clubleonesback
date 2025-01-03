package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEventoDTO {

    private Integer idTipoEvento;
    private String nombre;
    private String descripcion;
    private String modalidad;
    private String unidades;
    private String categoria;
    private Integer participantes;

}