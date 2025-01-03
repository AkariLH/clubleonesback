package com.akuzu.clubleones.entity;

import com.akuzu.clubleones.util.EstadoEvento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Date fechaInicioInscripciones;

    @Column(nullable = false)
    private Date fechaFinInscripciones;

    @Column(nullable = false)
    private Date fechaInicioEvento;

    @Column(nullable = false)
    private Date fechaFinEvento;

    @Column()
    private String horario;

    @Column(nullable = false, length = 50)
    private String modalidades;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String requisitos;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reglas;

    @ManyToOne
    @JoinColumn(name = "tipoevento")
    private TipoEvento tipoEvento;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Administracion entrenador;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administracion administrador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEvento estado;

}