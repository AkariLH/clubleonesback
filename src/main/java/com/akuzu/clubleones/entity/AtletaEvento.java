package com.akuzu.clubleones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "atleta_evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtletaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atleta", referencedColumnName = "idAtleta")
    private Atleta atleta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", referencedColumnName = "idEvento")
    private Evento evento;

    @Column(name = "participacion", columnDefinition = "json")
    @Convert(converter = ParticipacionConverter.class)
    private Map<String, Object> participacion;

}
