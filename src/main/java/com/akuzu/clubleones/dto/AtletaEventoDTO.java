package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtletaEventoDTO {

    private Integer idAtleta;
    private Integer idEvento;
    private String participacion;

}