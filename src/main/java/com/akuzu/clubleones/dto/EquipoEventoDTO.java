package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoEventoDTO {

    private Integer idEquipo;
    private Integer idEvento;
    private String participacion;
    private Integer numIntegrantesEvento;

}