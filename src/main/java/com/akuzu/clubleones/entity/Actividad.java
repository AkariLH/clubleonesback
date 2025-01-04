package com.akuzu.clubleones.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "actividad")
@Data
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_instalacion")
    private Instalacion instalacion;

    @Column
    private Date dia;

    @Column(name="hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;
}