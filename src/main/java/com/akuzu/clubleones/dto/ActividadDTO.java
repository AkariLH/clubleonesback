package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadDTO {

    private Integer id;
    private String nombre;
    private Integer idInstalacion;
    private Integer duracion;

}
