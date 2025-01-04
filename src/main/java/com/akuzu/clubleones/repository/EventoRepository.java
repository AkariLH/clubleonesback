package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

}