package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadEventoDTO {

    private Integer idActividad;
    private Integer idEvento;
    private Date horario;

}