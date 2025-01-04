package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.dto.*;
import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administradores")
public class AdministracionController {

    private static final Logger logger = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    private AdministracionService administracionService;

    @GetMapping
    public ResponseEntity<List<Administracion>> getAllAdministradores() {
        return new ResponseEntity<>(administracionService.getAllAdministradores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administracion> getAdministradorById(@PathVariable Integer id) {
        Optional<Administracion> administrador = administracionService.getAdministradorById(id);
        return administrador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Administracion> createAdministrador(@RequestBody Administracion administrador) {
        logger.info(administrador.toString());
        return new ResponseEntity<>(administracionService.createAdministrador(administrador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administracion> updateAdministrador(@PathVariable Integer id, @RequestBody Administracion administrador) {
        return new ResponseEntity<>(administracionService.updateAdministrador(id, administrador), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Integer id) {
        administracionService.deleteAdministrador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}