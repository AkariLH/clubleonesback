package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.repository.AdministracionRepository;
import com.akuzu.clubleones.repository.AtletaEventoRepository;
import com.akuzu.clubleones.service.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.akuzu.clubleones.repository.EventoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AtletaEventoRepository atletaEventoRepository;

    @Autowired 
    private AdministracionRepository administracionRepository;

    @Autowired
    private EventoRepository EventoRepository;

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
    @GetMapping("/por-fecha")
    public ResponseEntity<List<Evento>> getEventosPorFecha(
            @RequestParam("inicio") String inicio,
            @RequestParam("fin") String fin) {
        List<Evento> eventos = eventoService.getEventosPorFecha(inicio, fin);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Evento> cancelarEvento(@PathVariable Integer id) {
        try {
            Evento eventoCancelado = eventoService.cancelarEvento(id);
            return new ResponseEntity<>(eventoCancelado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/atletas/{eventoId}")
    public ResponseEntity<List<Atleta>> getEventosPorEventoId(@PathVariable Integer eventoId){
        Optional<Evento> evento = eventoService.getEventoById(eventoId);
        if(!evento.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<AtletaEvento> atletaEvento = new ArrayList<AtletaEvento>();
        Hibernate.initialize(atletaEvento);
        atletaEvento = atletaEventoRepository.findByEvento(evento.get());
        List<Atleta> atletas = atletaEvento.stream()
                .map(AtletaEvento::getAtleta)
                .collect(Collectors.toList());
        return new ResponseEntity<>(atletas, HttpStatus.OK);
    }

    @GetMapping("/entrenador/{entrenadorId}")
    public ResponseEntity<List<Evento>> getEventosPorEntrenadorId(@PathVariable Integer entrenadorId){
        Optional<Administracion> administracion = administracionRepository.findById(entrenadorId);
        if(!administracion.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<Evento> eventos = EventoRepository.findByEntrenador(administracion.get());
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }
}
