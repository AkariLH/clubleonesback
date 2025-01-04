package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Equipo;
import com.akuzu.clubleones.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> getEquipoById(Integer id) {
        return equipoRepository.findById(id);
    }

    public Equipo createEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo updateEquipo(Integer id, Equipo newEquipo) {
        return equipoRepository.findById(id).map(equipo -> {
            equipo.setNombreEquipo(newEquipo.getNombreEquipo());
            return equipoRepository.save(equipo);
        }).orElseThrow(() -> new RuntimeException("Equipo not found"));
    }

    public void deleteEquipo(Integer id) {
        equipoRepository.deleteById(id);
    }
}
