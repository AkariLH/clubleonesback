package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.TipoEvento;
import com.akuzu.clubleones.service.TipoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoeventos")
public class TipoEventoController {

    @Autowired
    private TipoEventoService tipoEventoService;

    @GetMapping
    public ResponseEntity<List<TipoEvento>> getAllTipoEventos() {
        return new ResponseEntity<>(tipoEventoService.getAllTipoEventos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEvento> getTipoEventoById(@PathVariable Integer id) {
        Optional<TipoEvento> tipoEvento = tipoEventoService.getTipoEventoById(id);
        return tipoEvento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TipoEvento> createTipoEvento(@RequestBody TipoEvento tipoEvento) {
        return new ResponseEntity<>(tipoEventoService.createTipoEvento(tipoEvento), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEvento> updateTipoEvento(@PathVariable Integer id, @RequestBody TipoEvento tipoEvento) {
        return new ResponseEntity<>(tipoEventoService.updateTipoEvento(id, tipoEvento), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoEvento(@PathVariable Integer id) {
        tipoEventoService.deleteTipoEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
