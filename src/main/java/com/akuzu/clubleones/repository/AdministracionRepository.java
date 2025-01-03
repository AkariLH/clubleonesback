package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Administracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministracionRepository extends JpaRepository<Administracion, Integer> {
    Optional<Administracion> findByCorreoAndContrasena(String correo, String contrasena);
}
