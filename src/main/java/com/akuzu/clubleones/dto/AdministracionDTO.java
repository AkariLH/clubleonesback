package com.akuzu.clubleones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministracionDTO {

    private Integer idAdministrador;
    private String nombre;
    private String correo;
    private String rol;

}