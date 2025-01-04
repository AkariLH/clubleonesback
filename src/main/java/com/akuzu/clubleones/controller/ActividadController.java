package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.repository.ActividadEventoRepository;
import com.akuzu.clubleones.service.ActividadService;
import com.akuzu.clubleones.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ActividadEventoRepository actividadEventoRepository;

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

    @PostMapping("/{actividadId}/eventos/{eventoId}")
    public ResponseEntity<String> registerActividadToEvento(
            @PathVariable Integer actividadId,
            @PathVariable Integer eventoId,
            @RequestParam String horario) {
        Optional<Actividad> actividadOpt = actividadService.getActividadById(actividadId);
        Optional<Evento> eventoOpt = eventoService.getEventoById(eventoId);

        if (actividadOpt.isEmpty()) {
            return new ResponseEntity<>("Actividad not found", HttpStatus.NOT_FOUND);
        }

        if (eventoOpt.isEmpty()) {
            return new ResponseEntity<>("Evento not found", HttpStatus.NOT_FOUND);
        }

        Actividad actividad = actividadOpt.get();
        Evento evento = eventoOpt.get();

        ActividadEvento actividadEvento = new ActividadEvento();
        actividadEvento.setActividad(actividad);
        actividadEvento.setEvento(evento);

        try {
            // Convert the "horario" string (time) to a Date object
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date horarioDate = timeFormat.parse(horario);
            actividadEvento.setHorario(horarioDate);
        } catch (ParseException e) {
            return new ResponseEntity<>("Invalid time format. Expected format: HH:mm:ss", HttpStatus.BAD_REQUEST);
        }

        actividadEventoRepository.save(actividadEvento);

        return new ResponseEntity<>("Actividad registered to Evento successfully", HttpStatus.OK);
    }

}
