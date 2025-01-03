package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtletaRepository extends JpaRepository<Atleta, Integer> {
    Optional<Atleta> findByCorreoAndContrasena(String correo, String contrasena);
}