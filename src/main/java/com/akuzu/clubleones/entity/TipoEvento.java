package com.akuzu.clubleones.entity;

import com.akuzu.clubleones.util.Categoria;
import com.akuzu.clubleones.util.Modalidad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TipoEvento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoEvento;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;

    @Column(columnDefinition = "JSON")
    private String unidades;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column
    private Integer participantes;

}
