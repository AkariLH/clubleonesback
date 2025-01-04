package com.akuzu.clubleones.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "actividadevento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
    private Actividad actividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", referencedColumnName = "idEvento")
    private Evento evento;

    @Column(nullable = false)
    private Date horario;
}
