package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.Actividad;
import com.akuzu.clubleones.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @GetMapping
    public ResponseEntity<List<Actividad>> getAllActividades() {
        return new ResponseEntity<>(actividadService.getAllActividades(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividad> getActividadById(@PathVariable Integer id) {
        Optional<Actividad> actividad = actividadService.getActividadById(id);
        return actividad.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Actividad> createActividad(@RequestBody Actividad actividad) {
        return new ResponseEntity<>(actividadService.createActividad(actividad), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actividad> updateActividad(@PathVariable Integer id, @RequestBody Actividad actividad) {
        return new ResponseEntity<>(actividadService.updateActividad(id, actividad), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActividad(@PathVariable Integer id) {
        actividadService.deleteActividad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
