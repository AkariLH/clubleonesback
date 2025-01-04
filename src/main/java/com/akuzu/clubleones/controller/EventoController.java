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
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        return new ResponseEntity<>(eventoService.getAllEventos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.createEvento(evento), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.updateEvento(id, evento), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        eventoService.deleteEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
