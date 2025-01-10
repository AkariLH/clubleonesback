package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.repository.AtletaEventoRepository;
import com.akuzu.clubleones.service.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/atletas")
public class AtletaController {

    @Autowired
    private AtletaService atletaService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AtletaEventoRepository atletaEventoRepository;

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

    @PostMapping("/{atletaId}/eventos/{eventoId}")
    public ResponseEntity<String> registerAtletaToEvento(@PathVariable Integer atletaId, @PathVariable Integer eventoId) {
        Optional<Atleta> atletaOpt = atletaService.getAtletaById(atletaId);
        Optional<Evento> eventoOpt = eventoService.getEventoById(eventoId);

        if (atletaOpt.isEmpty()) {
            return new ResponseEntity<>("Atleta not found", HttpStatus.NOT_FOUND);
        }

        if (eventoOpt.isEmpty()) {
            return new ResponseEntity<>("Evento not found", HttpStatus.NOT_FOUND);
        }

        Atleta atleta = atletaOpt.get();
        Evento evento = eventoOpt.get();

        AtletaEvento atletaEvento = new AtletaEvento();
        atletaEvento.setAtleta(atleta);
        atletaEvento.setEvento(evento);
        atletaEvento.setParticipacion(null);
        atletaEventoRepository.save(atletaEvento);

        return new ResponseEntity<>("Atleta registered to Evento successfully", HttpStatus.OK);
    }

    @GetMapping("/eventos/{atletaId}")
    public ResponseEntity<List<Evento>> getEventosPorIdAtleta(@PathVariable Integer atletaId){
        Optional<Atleta> atleta = atletaService.getAtletaById(atletaId);
        if(!atleta.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<AtletaEvento> atletaEvento = new ArrayList<AtletaEvento>();
        Hibernate.initialize(atletaEvento);
        atletaEvento = atletaEventoRepository.findByAtleta(atleta.get());
        List<Evento> eventos = atletaEvento.stream()
                .map(AtletaEvento::getEvento) // Extract the Evento object from each AtletaEvento
                .collect(Collectors.toList());
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }
}