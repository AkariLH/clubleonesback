package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}