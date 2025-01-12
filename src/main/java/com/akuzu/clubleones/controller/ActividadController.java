package com.akuzu.clubleones.controller;

import com.akuzu.clubleones.entity.*;
import com.akuzu.clubleones.service.ActividadService;
import com.akuzu.clubleones.service.EventoService;
import com.akuzu.clubleones.util.Unidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
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
    public ResponseEntity<List<Actividad>> createActividades(@RequestBody List<Actividad> actividades) {
        for (Actividad actividad : actividades) {
            if (actividad.getEvento() == null || actividad.getEvento().getIdEvento() == null) {
                return ResponseEntity.badRequest().build(); // Validar que cada actividad tenga un evento asociado
            }
            actividadService.createActividad(actividad);
        }
        return new ResponseEntity<>(actividades, HttpStatus.CREATED);
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
            @RequestParam String dia,
            @RequestParam String horaInicio,
            @RequestParam String horaFin,
            @RequestParam Unidades unidades) {
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

        try {
            // Convert the "dia" string to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date diaDate = dateFormat.parse(dia);
            actividad.setDia(diaDate);

            // Convert the "horaInicio" and "horaFin" strings to Time objects
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Time horaInicioTime = new Time(timeFormat.parse(horaInicio).getTime());
            Time horaFinTime = new Time(timeFormat.parse(horaFin).getTime());
            actividad.setHoraInicio(horaInicioTime);
            actividad.setHoraFin(horaFinTime);
        } catch (ParseException e) {
            return new ResponseEntity<>("Invalid date or time format. Expected formats: yyyy-MM-dd, HH:mm:ss", HttpStatus.BAD_REQUEST);
        }

        actividad.setUnidades(unidades);
        actividad.setEvento(evento);

        actividadService.updateActividad(actividadId, actividad);

        return new ResponseEntity<>("Actividad registered to Evento successfully", HttpStatus.OK);
    }

}
