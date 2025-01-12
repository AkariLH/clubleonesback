package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Administracion;
import com.akuzu.clubleones.entity.Evento;
import com.akuzu.clubleones.entity.TipoEvento;
import com.akuzu.clubleones.repository.AdministracionRepository;
import com.akuzu.clubleones.repository.EventoRepository;
import com.akuzu.clubleones.repository.TipoEventoRepository;
import com.akuzu.clubleones.util.EstadoEvento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
            evento.setNumintegrantes(newEvento.getNumintegrantes());
    
            if (newEvento.getTipoEvento() != null && newEvento.getTipoEvento().getIdTipoEvento() != null) {
                TipoEvento tipoEvento = tipoEventoRepository.findById(newEvento.getTipoEvento().getIdTipoEvento())
                    .orElseThrow(() -> new RuntimeException("TipoEvento no encontrado"));
                evento.setTipoEvento(tipoEvento);
            }
    
            if (newEvento.getEntrenador() != null && newEvento.getEntrenador().getIdAdministrador() != null) {
                Administracion entrenador = administracionRepository.findById(newEvento.getEntrenador().getIdAdministrador())
                    .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
                evento.setEntrenador(entrenador);
            }
    
            if (newEvento.getAdministrador() != null && newEvento.getAdministrador().getIdAdministrador() != null) {
                Administracion administrador = administracionRepository.findById(newEvento.getAdministrador().getIdAdministrador())
                    .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
                evento.setAdministrador(administrador);
            }
    
            evento.setEstado(newEvento.getEstado());
            return eventoRepository.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }    
    

    public List<Evento> getEventosPorFecha(String inicio, String fin) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        return eventoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    public Evento cancelarEvento(Integer id) {
        return eventoRepository.findById(id).map(evento -> {
            // Convertir el String "CANCELADO" a Enum
                evento.setEstado(EstadoEvento.valueOf("CANCELADO"));
                return eventoRepository.save(evento);
            }).orElseThrow(() -> new RuntimeException("Evento no encontrado"));    
    }   

}