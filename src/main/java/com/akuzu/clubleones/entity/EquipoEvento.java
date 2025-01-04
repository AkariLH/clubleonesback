package com.akuzu.clubleones.entity;

import com.akuzu.clubleones.util.ParticipacionConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "equipo_evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", referencedColumnName = "idEquipo")
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", referencedColumnName = "idEvento")
    private Evento evento;

    @Column(name = "participacion", columnDefinition = "json")
    @Convert(converter = ParticipacionConverter.class)
    private Map<String, Object> participacion;
}
