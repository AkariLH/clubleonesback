package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Actividad;
import com.akuzu.clubleones.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByEvento(Evento evento);
}