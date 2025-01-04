package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Administracion;
import com.akuzu.clubleones.entity.Evento;
import com.akuzu.clubleones.entity.TipoEvento;
import com.akuzu.clubleones.repository.AdministracionRepository;
import com.akuzu.clubleones.repository.EventoRepository;
import com.akuzu.clubleones.repository.TipoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private TipoEventoRepository tipoEventoRepository;

    @Autowired
    private AdministracionRepository administracionRepository;

    public Evento createEvento(Evento evento) {
        TipoEvento tipoEvento = evento.getTipoEvento();
        Optional<TipoEvento> tipoEventoO = tipoEventoRepository.findById(tipoEvento.getIdTipoEvento());
        if(tipoEventoO.isPresent()){
            tipoEvento = tipoEventoO.get();
        }
        evento.setTipoEvento(tipoEvento);

        Administracion entrenador = evento.getEntrenador();
        Optional<Administracion> entrenadorO = administracionRepository.findById(entrenador.getIdAdministrador());
        if (entrenadorO.isPresent()) {
            entrenador = entrenadorO.get();
        }
        evento.setEntrenador(entrenador);

        Administracion administrador = evento.getAdministrador();
        Optional<Administracion> administradorO = administracionRepository.findById(administrador.getIdAdministrador());
        if (administradorO.isPresent()) {
            administrador = administradorO.get();
        }
        evento.setAdministrador(administrador);
        return eventoRepository.save(evento);
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    public void deleteEvento(Integer id) {
        eventoRepository.deleteById(id);
    }

    public Evento updateEvento(Integer id, Evento newEvento) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNombre(newEvento.getNombre());
            evento.setFechaInicioInscripciones(newEvento.getFechaInicioInscripciones());
            evento.setFechaFinInscripciones(newEvento.getFechaFinInscripciones());
            evento.setFechaInicioEvento(newEvento.getFechaInicioEvento());
            evento.setFechaFinEvento(newEvento.getFechaFinEvento());
            evento.setModalidades(newEvento.getModalidades());
            evento.setCategoria(newEvento.getCategoria());
            evento.setCosto(newEvento.getCosto());
            evento.setDetalles(newEvento.getDetalles());
            evento.setTipoEvento(newEvento.getTipoEvento());
            evento.setEntrenador(newEvento.getEntrenador());
            evento.setAdministrador(newEvento.getAdministrador());
            evento.setEstado(newEvento.getEstado());
            return eventoRepository.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento not found"));
    }

}