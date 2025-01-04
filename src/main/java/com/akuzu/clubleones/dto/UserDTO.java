package com.akuzu.clubleones.dto;

public class UserDTO {
    private String nombre;
    private String correo;
    private String tipoUsuario;

    public UserDTO(String nombre, String correo, String tipoUsuario) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
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
}
