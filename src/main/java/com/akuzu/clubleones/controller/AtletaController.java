package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.dto.*;
import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atletas")
public class AtletaController {

    @Autowired
    private AtletaService atletaService;

    @GetMapping
    public ResponseEntity<List<Atleta>> getAllAtletas() {
        return new ResponseEntity<>(atletaService.getAllAtletas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atleta> getAtletaById(@PathVariable Integer id) {
        Optional<Atleta> atleta = atletaService.getAtletaById(id);
        return atleta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Atleta> createAtleta(@RequestBody Atleta atleta) {
        return new ResponseEntity<>(atletaService.createAtleta(atleta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atleta> updateAtleta(@PathVariable Integer id, @RequestBody Atleta atleta) {
        return new ResponseEntity<>(atletaService.updateAtleta(id, atleta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtleta(@PathVariable Integer id) {
        atletaService.deleteAtleta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}