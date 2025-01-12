package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.Actividad;
import com.akuzu.clubleones.entity.Instalacion;
import com.akuzu.clubleones.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionService instalacionService;

    @GetMapping
    public ResponseEntity<List<Instalacion>> getAllInstalaciones() {
        return new ResponseEntity<>(instalacionService.getAllInstalaciones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instalacion> getInstalacionById(@PathVariable Integer id) {
        Optional<Instalacion> instalacion = instalacionService.getInstalacionById(id);
        return instalacion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Instalacion> createInstalacion(@RequestBody Instalacion instalacion) {
        return new ResponseEntity<>(instalacionService.createInstalacion(instalacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instalacion> updateInstalacion(@PathVariable Integer id, @RequestBody Instalacion instalacion) {
        return new ResponseEntity<>(instalacionService.updateInstalacion(id, instalacion), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstalacion(@PathVariable Integer id) {
        instalacionService.deleteInstalacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/actividades/{idInstalacion}")
    public ResponseEntity<List<Actividad>> getActividadesByInstalacion(@PathVariable Integer idInstalacion) {
        return new ResponseEntity<>(instalacionService.getActividadesByInstalacion(idInstalacion), HttpStatus.OK);
    }
}