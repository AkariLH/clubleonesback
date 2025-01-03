package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Instalacion;
import com.akuzu.clubleones.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository instalacionRepository;

    public List<Instalacion> getAllInstalaciones() {
        return instalacionRepository.findAll();
    }

    public Optional<Instalacion> getInstalacionById(Integer id) {
        return instalacionRepository.findById(id);
    }

    public Instalacion createInstalacion(Instalacion instalacion) {
        return instalacionRepository.save(instalacion);
    }

    public Instalacion updateInstalacion(Integer id, Instalacion newInstalacion) {
        return instalacionRepository.findById(id).map(instalacion -> {
            instalacion.setNombre(newInstalacion.getNombre());
            instalacion.setDescripcion(newInstalacion.getDescripcion());
            return instalacionRepository.save(instalacion);
        }).orElseThrow(() -> new RuntimeException("Instalacion not found"));
    }

    public void deleteInstalacion(Integer id) {
        instalacionRepository.deleteById(id);
    }
}