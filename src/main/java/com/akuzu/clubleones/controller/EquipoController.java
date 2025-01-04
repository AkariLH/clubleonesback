package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.repository.EquipoEventoRepository;
import com.akuzu.clubleones.service.EquipoService;
import com.akuzu.clubleones.service.EventoService;
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

    @Autowired
    private EventoService eventoService;

    @Autowired
    private EquipoEventoRepository equipoEventoRepository;

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

    @PostMapping("/{equipoId}/eventos/{eventoId}")
    public ResponseEntity<String> registerAtletaToEvento(@PathVariable Integer equipoId, @PathVariable Integer eventoId) {
        Optional<Equipo> equipoOpt = equipoService.getEquipoById(equipoId);
        Optional<Evento> eventoOpt = eventoService.getEventoById(eventoId);

        if (equipoOpt.isEmpty()) {
            return new ResponseEntity<>("Equipo not found", HttpStatus.NOT_FOUND);
        }

        if (eventoOpt.isEmpty()) {
            return new ResponseEntity<>("Evento not found", HttpStatus.NOT_FOUND);
        }

        Equipo equipo = equipoOpt.get();
        Evento evento = eventoOpt.get();

        EquipoEvento equipoEvento = new EquipoEvento();
        equipoEvento.setEquipo(equipo);
        equipoEvento.setEvento(evento);
        equipoEvento.setParticipacion(null);
        equipoEventoRepository.save(equipoEvento);

        return new ResponseEntity<>("Equipo registered to Evento successfully", HttpStatus.OK);
    }
}
