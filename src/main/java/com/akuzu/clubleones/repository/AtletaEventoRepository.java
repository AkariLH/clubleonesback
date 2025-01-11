package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Atleta;
import com.akuzu.clubleones.entity.AtletaEvento;
import com.akuzu.clubleones.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtletaEventoRepository extends JpaRepository<AtletaEvento, Integer> {
    List<AtletaEvento> findByAtleta(Atleta atleta);
    List<AtletaEvento> findByEvento(Evento evento);
    boolean existsByAtletaAndEvento(Atleta atleta, Evento evento);
}
