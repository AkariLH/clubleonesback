package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.dto.LoginRequest;
import com.akuzu.clubleones.dto.UserDTO;
import com.akuzu.clubleones.entity.Administracion;
import com.akuzu.clubleones.entity.Atleta;
import com.akuzu.clubleones.repository.AdministracionRepository;
import com.akuzu.clubleones.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AdministracionRepository administracionRepository;

    @Autowired
    private AtletaRepository atletaRepository;

    @PostMapping
public ResponseEntity<UserDTO> login(@RequestBody LoginRequest loginRequest) {
    // Check for Administrador or Entrenador
    Optional<Administracion> adminOpt = administracionRepository.findByCorreoAndContrasena(
            loginRequest.getCorreo(), loginRequest.getContrasena()
    );

    if (adminOpt.isPresent()) {
        Administracion admin = adminOpt.get();
        UserDTO userDTO = new UserDTO(admin.getNombre(), admin.getCorreo(), admin.getRol().toString(), admin.getIdAdministrador());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // Check for Atleta
    Optional<Atleta> atletaOpt = atletaRepository.findByCorreoAndContrasena(
            loginRequest.getCorreo(), loginRequest.getContrasena()
    );

    if (atletaOpt.isPresent()) {
        Atleta atleta = atletaOpt.get();
        UserDTO userDTO = new UserDTO(atleta.getNombre(), atleta.getCorreo(), "Atleta", atleta.getIdAtleta());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
}

}