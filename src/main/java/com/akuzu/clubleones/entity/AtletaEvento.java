package com.akuzu.clubleones.entity;

import com.akuzu.clubleones.util.ParticipacionConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "atleta_evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtletaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atleta", referencedColumnName = "idAtleta")
    private Atleta atleta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", referencedColumnName = "idEvento")
    private Evento evento;

    @JsonIgnore
    @Column(name = "participacion", columnDefinition = "json")
    @Convert(converter = ParticipacionConverter.class)
    private Map<String, Object> participacion;
}