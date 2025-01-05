package com.akuzu.clubleones.repository;

import com.akuzu.clubleones.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("SELECT e FROM Evento e WHERE e.fechaInicioEvento BETWEEN :inicio AND :fin")
List<Evento> findByFechaBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

}