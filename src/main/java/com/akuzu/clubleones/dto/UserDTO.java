package com.akuzu.clubleones.dto;

public class UserDTO {
    private String nombre;
    private String correo;
    private String tipoUsuario;
    private Integer id; // Agregar campo para el ID del usuario

    public UserDTO(String nombre, String correo, String tipoUsuario, Integer id) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.id = id; // Inicializar el ID
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public Integer getId() {
        return id; // Getter para el ID
    }
}
