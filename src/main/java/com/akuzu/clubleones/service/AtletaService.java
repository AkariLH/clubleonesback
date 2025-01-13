package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Atleta;
import com.akuzu.clubleones.repository.AtletaEventoRepository;
import com.akuzu.clubleones.repository.AtletaRepository;
import com.akuzu.clubleones.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private AtletaEventoRepository atletaEventoRepository;

    public Atleta createAtleta(Atleta atleta) {
        return atletaRepository.save(atleta);
    }

    public List<Atleta> getAllAtletas() {
        return atletaRepository.findAll();
    }

    public Optional<Atleta> getAtletaById(Integer id) {
        return atletaRepository.findById(id);
    }

    public void deleteAtleta(Integer id) {
        atletaRepository.deleteById(id);
    }

    public Atleta updateAtleta(Integer id, Atleta newAtleta) {
        return atletaRepository.findById(id).map(atleta -> {
            atleta.setNombre(newAtleta.getNombre());
            atleta.setApellidoPaterno(newAtleta.getApellidoPaterno());
            atleta.setApellidoMaterno(newAtleta.getApellidoMaterno());
            atleta.setFechaDeNacimiento(newAtleta.getFechaDeNacimiento());
            atleta.setSexo(newAtleta.getSexo());
            atleta.setCorreo(newAtleta.getCorreo());
            atleta.setContrasena(newAtleta.getContrasena());
            atleta.setPeso(newAtleta.getPeso());
            atleta.setEstatura(newAtleta.getEstatura());
            atleta.setEquipo(newAtleta.getEquipo());
            return atletaRepository.save(atleta);
        }).orElseThrow(() -> new RuntimeException("Atleta not found"));
    }
}
