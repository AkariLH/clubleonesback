package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Actividad;
import com.akuzu.clubleones.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public List<Actividad> getAllActividades() {
        return actividadRepository.findAll();
    }

    public Optional<Actividad> getActividadById(Integer id) {
        return actividadRepository.findById(id);
    }

    public Actividad createActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public Actividad updateActividad(Integer id, Actividad newActividad) {
        return actividadRepository.findById(id).map(actividad -> {
            actividad.setNombre(newActividad.getNombre());
            actividad.setInstalacion(newActividad.getInstalacion());
            actividad.setDuracion(newActividad.getDuracion());
            return actividadRepository.save(actividad);
        }).orElseThrow(() -> new RuntimeException("Actividad not found"));
    }

    public void deleteActividad(Integer id) {
        actividadRepository.deleteById(id);
    }
}