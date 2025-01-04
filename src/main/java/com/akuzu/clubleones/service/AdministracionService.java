package com.akuzu.clubleones.service;

import com.akuzu.clubleones.entity.Administracion;
import com.akuzu.clubleones.repository.AdministracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministracionService {

    @Autowired
    private AdministracionRepository administracionRepository;

    public Administracion createAdministrador(Administracion administrador) {
        return administracionRepository.save(administrador);
    }

    public List<Administracion> getAllAdministradores() {
        return administracionRepository.findAll();
    }

    public Optional<Administracion> getAdministradorById(Integer id) {
        return administracionRepository.findById(id);
    }

    public void deleteAdministrador(Integer id) {
        administracionRepository.deleteById(id);
    }

    public Administracion updateAdministrador(Integer id, Administracion newAdministrador) {
        return administracionRepository.findById(id).map(administrador -> {
            administrador.setNombre(newAdministrador.getNombre());
            administrador.setCorreo(newAdministrador.getCorreo());
            administrador.setContrasena(newAdministrador.getContrasena());
            administrador.setRol(newAdministrador.getRol());
            return administracionRepository.save(administrador);
        }).orElseThrow(() -> new RuntimeException("Administrador not found"));
    }
}

