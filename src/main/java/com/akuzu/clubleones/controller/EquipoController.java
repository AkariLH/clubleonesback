package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.Equipo;
import com.akuzu.clubleones.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        return new ResponseEntity<>(equipoService.getAllEquipos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Integer id) {
        Optional<Equipo> equipo = equipoService.getEquipoById(id);
        return equipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        return new ResponseEntity<>(equipoService.createEquipo(equipo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Integer id, @RequestBody Equipo equipo) {
        return new ResponseEntity<>(equipoService.updateEquipo(id, equipo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Integer id) {
        equipoService.deleteEquipo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
