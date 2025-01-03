package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.TipoEvento;
import com.akuzu.clubleones.repository.TipoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEventoService {

    @Autowired
    private TipoEventoRepository tipoEventoRepository;

    public List<TipoEvento> getAllTipoEventos() {
        return tipoEventoRepository.findAll();
    }

    public Optional<TipoEvento> getTipoEventoById(Integer id) {
        return tipoEventoRepository.findById(id);
    }

    public TipoEvento createTipoEvento(TipoEvento tipoEvento) {
        return tipoEventoRepository.save(tipoEvento);
    }

    public TipoEvento updateTipoEvento(Integer id, TipoEvento newTipoEvento) {
        return tipoEventoRepository.findById(id).map(tipoEvento -> {
            tipoEvento.setNombre(newTipoEvento.getNombre());
            tipoEvento.setDescripcion(newTipoEvento.getDescripcion());
            tipoEvento.setModalidad(newTipoEvento.getModalidad());
            tipoEvento.setUnidades(newTipoEvento.getUnidades());
            tipoEvento.setCategoria(newTipoEvento.getCategoria());
            tipoEvento.setParticipantes(newTipoEvento.getParticipantes());
            return tipoEventoRepository.save(tipoEvento);
        }).orElseThrow(() -> new RuntimeException("TipoEvento not found"));
    }

    public void deleteTipoEvento(Integer id) {
        tipoEventoRepository.deleteById(id);
    }
}
